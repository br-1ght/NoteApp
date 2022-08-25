package com.br1ght.Services;

import com.br1ght.Models.NoteBucket;

public class ViewService extends BaseService{

    public ViewService(NoteBucket noteBucket) {
        super(noteBucket);
    }

    @Override
    public void run() {
        System.out.println(noteBucket.viewNotes());
    }
}
