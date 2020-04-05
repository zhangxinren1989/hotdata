package disappear.programmer.service.hotdata.dao.mapper.hotwords;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import disappear.programmer.service.hotdata.dao.model.hotwords.DailyHotWords;

public interface DailyHotWordsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DailyHotWords record);

    int insertSelective(DailyHotWords record);
    
    // 批量插入
    void insertBatch(@Param("dailyHotWords")List<DailyHotWords> dailyHotWords);

    DailyHotWords selectByPrimaryKey(Long id);
    
    // 根据keyWord查询记录
    DailyHotWords selectDailyByKeyWord(@Param("keyWord")String keyWord, @Param("date")Date date);
    
    // 查询当天的热词
    List<DailyHotWords> selectDailyHotWords(@Param("size")int size, @Param("date")Date date, @Param("source")String source);

    int updateByPrimaryKeySelective(DailyHotWords record);

    int updateByPrimaryKeyWithBLOBs(DailyHotWords record);

    int updateByPrimaryKey(DailyHotWords record);
    
    // 批量更新
    void updateBatch(@Param("dailyHotWords")List<DailyHotWords> dailyHotWords);
}