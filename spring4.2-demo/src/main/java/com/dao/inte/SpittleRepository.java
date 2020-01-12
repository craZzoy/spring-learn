package com.dao.inte;

import com.vo.Spitter;

import java.util.List;

/**
 * Created by zwz on 2019/5/16.
 */
public interface SpittleRepository {
    List<Spitter> findSpittle(long max, int count);

    Spitter save(Spitter spittle);

    Spitter findByUsername(String username);

    Spitter findOne();
}
