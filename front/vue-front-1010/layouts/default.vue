<template>
    <div class="in-wrap">
      <!-- 公共头引入 -->
      <header id="header">
        <section class="container">
          <h1 id="logo">
            <a href="#" title="Easy Learn">
              <img src="~/assets/img/logo.png" width="100%" alt="Easy Learn">
            </a>
          </h1>
          <div class="h-r-nsl">
            <ul class="nav">
              <router-link to="/" tag="li" active-class="current" exact>
                <a>Home</a>
              </router-link>
              <router-link to="/course" tag="li" active-class="current">
                <a>Courses</a>
              </router-link>
              <router-link to="/teacher" tag="li" active-class="current">
                <a>Tutors</a>
              </router-link>
              <router-link to="/article" tag="li" active-class="current">
                <a>Articles</a>
              </router-link>
              <router-link to="/qa" tag="li" active-class="current">
                <a>Q & A</a>
              </router-link>
            </ul>
            <!-- / nav -->
            <ul class="h-r-login">
              <li id="no-login">
                <a href="/sing_in" title="Login">
                  <em class="icon18 login-icon">&nbsp;</em>
                  <span class="vam ml5">Login</span>
                </a>
                |
                <a href="/sign_up" title="Register">
                  <span class="vam ml5">Register</span>
                </a>
              </li>
              <li class="mr10 undis" id="is-login-one">
                <a href="#" title="Message" id="headerMsgCountId">
                  <em class="icon18 news-icon">&nbsp;</em>
                </a>
                <q class="red-point" style="display: none">&nbsp;</q>

              </li>
              <li class="h-r-user undis" id="is-login-two">
                <a href="#" title>
                  <img
                    src="~/assets/img/avatar-boy.gif"
                    width="30"
                    height="30"
                    class="vam picImg"
                    alt
                  >
                  <span class="vam disIb" id="userName"></span>
                </a>
                <a href="javascript:void(0)" title="Quit" onclick="exit();" class="ml5">Quit</a>
              </li>
              <!-- /未登录显示第1 li；登录后显示第2，3 li -->
            </ul>
            <aside class="h-r-search">
              <form action="#" method="post">
                <label class="h-r-s-box">
                  <input type="text" placeholder="输入你想学的课程" name="queryCourse.courseName" value>
                  <button type="submit" class="s-btn">
                    <em class="icon18">&nbsp;</em>
                  </button>
                </label>
              </form>
            </aside>
          </div>
          <aside class="mw-nav-btn">
            <div class="mw-nav-icon"></div>
          </aside>
          <div class="clear"></div>
        </section>
      </header>
      <!-- /公共头引入 -->

      <nuxt/>
      <!-- 公共底引入 -->
      <footer id="footer">
        <section class="container">
          <div class>
            <h4 class="hLh30">
              <span class="fsize18 f-fM c-999">友情链接</span>
            </h4>
            <ul class="of flink-list">
              <li>
                <a href="http://www.atguigu.com/" title="尚硅谷" target="_blank">尚硅谷</a>
              </li>
            </ul>
            <div class="clear"></div>
          </div>
          <div class="b-foot">
            <section class="fl col-7">
              <section class="mr20">
                <section class="b-f-link">
                  <a href="#" title="About Us" target="_blank">About Us</a>|
                  <a href="#" title="Connect Us" target="_blank">Connect Us</a>|
                  <a href="#" title="Help Center" target="_blank">Help Center</a>|
                  <a href="#" title="Resource Download" target="_blank">Resource Download</a>|
                  <span>Tel：010-00000000 0755-00000000</span>
                  <span>Email：xiangyiwen0218@gmail.com</span>
                </section>
                <section class="b-f-link mt10">
                  <span>©2018课程版权均归谷粒学院所有 京ICP备17055252号</span>
                </section>
              </section>
            </section>
            <aside class="fl col-3 tac mt15">
              <section class="gf-tx">
                <span>
                  <img src="~/assets/img/wx-icon.png" alt>
                </span>
              </section>
              <section class="gf-tx">
                <span>
                  <img src="~/assets/img/wb-icon.png" alt>
                </span>
              </section>
            </aside>
            <div class="clear"></div>
          </div>
        </section>
      </footer>
      <!-- /公共底引入 -->
    </div>
  </template>
  <script>
  import "~/assets/css/reset.css";
  import "~/assets/css/theme.css";
  import "~/assets/css/global.css";
  import "~/assets/css/web.css";
  export default {};
  </script>