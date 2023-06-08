package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }
    //todo
    @DeleteMapping("id")
    public R deleteVideo(@PathVariable String id){
        EduVideo eduVideo=videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(StringUtils.isEmpty(videoSourceId)) {
            R result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode()==20001){
                throw new GuliException(20001,"Fail to delete the video, fuse...");
            }
        }

        videoService.removeById(id);
        return R.ok();
    }

}

