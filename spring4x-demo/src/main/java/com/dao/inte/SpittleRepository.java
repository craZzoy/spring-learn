package com.dao.inte;

import com.vo.NewSpittle;
import com.vo.Spittle;

import java.util.List;

/**
 * Created by zwz on 2019/5/16.
 */
public interface SpittleRepository {
    List<Spittle> findSpittle(long max, int count);

    NewSpittle save(NewSpittle spittle);

    NewSpittle findByUsername(String username);

    Spittle findOne();
}
