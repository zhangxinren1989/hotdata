package disappear.programmer.service.hotdata.dao.mapper.hotwords;

import disappear.programmer.service.hotdata.dao.model.hotwords.MonthlyHotWords;

public interface MonthlyHotWordsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonthlyHotWords record);

    int insertSelective(MonthlyHotWords record);

    MonthlyHotWords selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyHotWords record);

    int updateByPrimaryKeyWithBLOBs(MonthlyHotWords record);

    int updateByPrimaryKey(MonthlyHotWords record);
}