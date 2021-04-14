package com.yunfatong.evidence.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yunfatong.evidence.controller.base.BaseController;
import com.yunfatong.evidence.mapper.RegionAmapMapperCustom;
import com.yunfatong.evidence.mapper.RegionOrderMapper;
import com.yunfatong.evidence.model.RegionAmap;
import com.yunfatong.evidence.model.RegionAmapExample;
import com.yunfatong.evidence.model.RegionOrder;
import com.yunfatong.evidence.utils.HttpClientResult;
import com.yunfatong.evidence.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserMasterController extends BaseController {

    @Autowired
    private RegionAmapMapperCustom regionAmapMapperCustom;
    @Autowired
    private RegionOrderMapper regionOrderMapper;

    @RequestMapping("/getAll")
    public Object getMasterList() throws Exception {
        HttpClientResult result = HttpClientUtils.doGet("https://restapi.amap.com/v3/config/district?subdistrict=4&key=1e1f1c04c8b72367efe4431de2f038f5");
        String jsonString = result.getContent();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONArray countryAll = jsonObject.getJSONArray("districts");
        for (int i = 0; i < countryAll.size(); i++) {
            JSONObject countryLeve0 = countryAll.getJSONObject(i);
            String citycode0 = countryLeve0.getString("citycode");
            String adcode0 = countryLeve0.getString("adcode");
            String name0 = countryLeve0.getString("name");
            String center0 = countryLeve0.getString("center");
            String country = countryLeve0.getString("level");
            int level = 0;
            if (country.equals("country")) {
                level = 0;
            }
            //插入国家
            Integer id1 = insert(0, adcode0, citycode0, name0, center0, level, name0);
            JSONArray province0 = countryLeve0.getJSONArray("districts");

            for (int j = 0; j < province0.size(); j++) {
                JSONObject province1 = province0.getJSONObject(j);
                String citycode1 = province1.getString("citycode");
                String adcode1 = province1.getString("adcode");
                String name1 = province1.getString("name");
                String center1 = province1.getString("center");
                String province = province1.getString("level");
                int level1 = 0;
                if (province.equals("province")) {
                    level1 = 1;
                }
                //插入省
                Integer id2 = insert(id1, adcode1, citycode1, name1, center1, level1, name0, name1);
                JSONArray city0 = province1.getJSONArray("districts");

                for (int z = 0; z < city0.size(); z++) {
                    JSONObject city2 = city0.getJSONObject(z);
                    String citycode2 = city2.getString("citycode");
                    String adcode2 = city2.getString("adcode");
                    String name2 = city2.getString("name");
                    String center2 = city2.getString("center");
                    String city = city2.getString("level");
                    int level2 = 0;
                    if (city.equals("city")) {
                        level2 = 2;
                    }
                    //插入市
                    Integer id3 = insert(id2, adcode2, citycode2, name2, center2, level2, name0, name1, name2);

                    JSONArray street0 = city2.getJSONArray("districts");
                    for (int w = 0; w < street0.size(); w++) {
                        JSONObject street3 = street0.getJSONObject(w);
                        String citycode3 = street3.getString("citycode");
                        String adcode3 = street3.getString("adcode");
                        String name3 = street3.getString("name");
                        String center3 = street3.getString("center");
                        String street = street3.getString("level");
                        int level3 = 0;
                        if (street.equals("street")) {
                            level3 = 2;
                        }
                        //插入区县
                        insert(id3, adcode3, citycode3, name3, center3, level3, name0, name1, name2, name3);
                        //  JSONArray street = street3.getJSONArray("districts");
                        //有需要可以继续向下遍历

                    }
                }
            }
        }
        return getSuccessResult();
    }

    /**
     * 公共的插入方法
     *
     * @param parentId  父id
     * @param citycode  城市编码
     * @param adcode    区域编码  街道没有独有的adcode，均继承父类（区县）的adcode
     * @param name      城市名称 （行政区名称）
     * @param center    地理坐标
     * @param level     等级  0 国家，1省，...依次排
     * @param mergeName 把名称拼接成全名
     * @return
     */
    public Integer insert(Integer parentId, String citycode, String adcode, String name, String center, int level, String... mergeName) {
        RegionAmap record = new RegionAmap();
        if (!adcode.equals("[]")) {
            record.setAdcode(adcode);
        }
        record.setCitycode(Integer.parseInt(citycode));
        record.setCenter(center);
        record.setLevel(level);
        record.setName(name);
        record.setParentId(parentId);
        String megName = "";
        for (int i = 0; i < mergeName.length; i++) {
            megName = megName + mergeName[i];
            if (i < mergeName.length - 1) {
                megName = megName + ",";
            }
        }
        record.setMergerName(megName);
        regionAmapMapperCustom.insertSelective(record);
        return record.getCitycode();
    }

    /**
     * 生成的sql顺序重新排序
     *
     * @return
     */
    @RequestMapping("/order")
    public Object order() {
        RegionAmapExample example = new RegionAmapExample();
        example.setOrderByClause("citycode, adcode");
        List<RegionAmap> selectByExample = regionAmapMapperCustom.selectByExample(example);
        for (RegionAmap regionAmap : selectByExample) {
            RegionOrder record = new RegionOrder();
            record.setAdcode(regionAmap.getAdcode());
            record.setCitycode(regionAmap.getCitycode());
            record.setCenter(regionAmap.getCenter());
            record.setLevel(regionAmap.getLevel());
            record.setMergerName(regionAmap.getMergerName());
            record.setName(regionAmap.getName());
            record.setParentId(regionAmap.getParentId());
            regionOrderMapper.insertSelective(record);
        }
        return getSuccessResult();
    }

}
