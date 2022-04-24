package com.pyk.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pyk.canteen.model.entity.Cuisine;
import org.apache.ibatis.annotations.Select;

public interface CuisineMapper extends BaseMapper<Cuisine> {
    @Select("select name from t_cuisine where id=${id}")
    String selectNameById(Integer id);
}
