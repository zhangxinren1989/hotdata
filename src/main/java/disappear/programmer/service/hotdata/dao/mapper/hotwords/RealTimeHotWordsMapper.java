package disappear.programmer.service.hotdata.dao.mapper.hotwords;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import disappear.programmer.service.hotdata.dao.model.hotwords.RealTimeHotWords;

public interface RealTimeHotWordsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RealTimeHotWords record);
    
    // 批量插入
    void insertBatch(@Param("realTimeHotWords")List<RealTimeHotWords> realTimeHotWords);

    int insertSelective(RealTimeHotWords record);

    RealTimeHotWords selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RealTimeHotWords record);

    int updateByPrimaryKeyWithBLOBs(RealTimeHotWords record);

    int updateByPrimaryKey(RealTimeHotWords record);
    
    // 批量更新
    void updateBatch(@Param("realTimeHotWords")List<RealTimeHotWords> realTimeHotWords);
    
    // 根据keyWord查询记录
    RealTimeHotWords selectByKeyWord(String keyWord);
    
    // 获取最新50条实时数据
    List<RealTimeHotWords> selectRealTimeHotWords(@Param("size")int size, @Param("source")String source);
}