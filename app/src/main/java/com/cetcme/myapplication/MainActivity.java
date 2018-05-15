/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cetcme.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/*
 * MainActivity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity {

    private Banner banner;
    private List<Class<? extends ViewPager.PageTransformer>> animationTypeList = new ArrayList<>();
    private int[] animationScrollTime = new int[]{
      550, 550, 750, 750, 750, 750, 750, 750, 750, 750, 750, 750, 1250, 1250, 1250
    };
    private Integer[] images;
    private int animationIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new Integer[] { R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3, R.mipmap.banner_4, R.mipmap.banner_5};
        initAnimationType();
        initBanner();

        // 每十秒切换动画
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (animationIndex == animationTypeList.size()) animationIndex = 0;
                banner.setBannerAnimation(animationTypeList.get(animationIndex));
                banner.setScrollerTime(animationScrollTime[animationIndex]);
                animationIndex++;
            }
        }, 0, 10000);
    }

    private void initAnimationType() {
        animationTypeList.add(Transformer.Default); // 550
        animationTypeList.add(Transformer.Accordion);
        animationTypeList.add(Transformer.BackgroundToForeground); // 750
        animationTypeList.add(Transformer.ForegroundToBackground);
        animationTypeList.add(Transformer.CubeIn); // 750
        animationTypeList.add(Transformer.CubeOut);
        animationTypeList.add(Transformer.DepthPage); // 750
//        animationTypeList.add(Transformer.FlipHorizontal); //
//        animationTypeList.add(Transformer.FlipVertical);
        animationTypeList.add(Transformer.RotateDown); // 750
        animationTypeList.add(Transformer.RotateUp);
        animationTypeList.add(Transformer.ScaleInOut); // 750
        animationTypeList.add(Transformer.Stack); // 750
        animationTypeList.add(Transformer.Tablet); // 750
        animationTypeList.add(Transformer.ZoomIn); // 1250
        animationTypeList.add(Transformer.ZoomOut); //
        animationTypeList.add(Transformer.ZoomOutSlide); // 1250
    }

    private void initBanner() {
        banner = findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImages(images);
        banner.setDelayTime(5000);
        banner.setScrollerTime(550);
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
    }

}
