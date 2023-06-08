package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VodFileDegradeFeignClient implements VodClient{


    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("Error deleting video");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("Error deleting videos");
    }
}
