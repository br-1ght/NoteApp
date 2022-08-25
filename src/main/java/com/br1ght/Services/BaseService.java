package com.br1ght.Services;

import com.br1ght.Models.NoteBucket;

public abstract class BaseService {
    final NoteBucket noteBucket;

    public BaseService(NoteBucket noteBucket) {
        this.noteBucket = noteBucket;
    }

    public abstract void run();
}
