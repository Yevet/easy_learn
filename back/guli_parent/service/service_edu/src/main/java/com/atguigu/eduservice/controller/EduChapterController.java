package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list=chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.ok();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter=chapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag=chapterService.deleteChapter(chapterId);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }

    }
}


