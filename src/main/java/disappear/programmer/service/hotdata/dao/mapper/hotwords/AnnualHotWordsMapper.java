package disappear.programmer.service.hotdata.dao.mapper.hotwords;

import disappear.programmer.service.hotdata.dao.model.hotwords.AnnualHotWords;

public interface AnnualHotWordsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AnnualHotWords record);

    int insertSelective(AnnualHotWords record);

    AnnualHotWords selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AnnualHotWords record);

    int updateByPrimaryKeyWithBLOBs(AnnualHotWords record);

    int updateByPrimaryKey(AnnualHotWords record);
}