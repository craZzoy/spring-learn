package com.dao;

import com.dao.inte.SpittleRepository;
import com.vo.Spitter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zwz on 2019/5/19.
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spitter> findSpittle(long max, int count) {
        return null;
    }

    @Override
    public Spitter save(Spitter spittle) {
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }

    @Override
    public Spitter findOne() {
        return null;
    }
}
