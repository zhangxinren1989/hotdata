package disappear.programmer.service.hotdata.dao.mapper.hotwords;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import disappear.programmer.service.hotdata.dao.model.hotwords.WeeklyHotWords;

public interface WeeklyHotWordsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeeklyHotWords record);
    
    // 批量插入
    void insertBatch(@Param("weeklyHotWords")List<WeeklyHotWords> weeklyHotWords);

    int insertSelective(WeeklyHotWords record);

    WeeklyHotWords selectByPrimaryKey(Long id);
    
    // 根据keyWord查询记录
    WeeklyHotWords selectWeeklyByKeyWord(@Param("keyWord")String keyWord, @Param("weekEnd")Date weekEnd);
    
    // 查询当天的热词
    List<WeeklyHotWords> selectWeeklyHotWords(@Param("size")int size, @Param("weekEnd")Date date, @Param("source")String source);

    int updateByPrimaryKeySelective(WeeklyHotWords record);

    int updateByPrimaryKeyWithBLOBs(WeeklyHotWords record);

    int updateByPrimaryKey(WeeklyHotWords record);
    
    // 批量更新
    void updateBatch(@Param("weeklyHotWords")List<WeeklyHotWords> weeklyHotWords);
}