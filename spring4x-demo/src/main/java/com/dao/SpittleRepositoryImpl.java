package com.dao;

import com.dao.inte.SpittleRepository;
import com.vo.NewSpittle;
import com.vo.Spittle;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zwz on 2019/5/19.
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittle(long max, int count) {
        return null;
    }

    @Override
    public NewSpittle save(NewSpittle spittle) {
        return null;
    }

    @Override
    public NewSpittle findByUsername(String username) {
        return null;
    }

    @Override
    public Spittle findOne() {
        return null;
    }
}
