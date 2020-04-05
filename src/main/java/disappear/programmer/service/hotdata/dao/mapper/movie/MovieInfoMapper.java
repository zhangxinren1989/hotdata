package disappear.programmer.service.hotdata.dao.mapper.movie;

import disappear.programmer.service.hotdata.dao.model.movie.MovieInfo;

public interface MovieInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieInfo record);

    int insertSelective(MovieInfo record);

    MovieInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MovieInfo record);

    int updateByPrimaryKey(MovieInfo record);
}