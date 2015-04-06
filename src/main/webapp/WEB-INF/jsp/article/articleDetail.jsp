
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Blog Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/blog.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <div class="blog-header">
        <h1 class="blog-title">Xingxing's Blog</h1>
        <p class="lead blog-description">记录每一点的成长!!!</p>
      </div>

      <div class="row">

        <div class="col-sm-8 blog-main">

          <div class="blog-post">
            <h2 class="blog-post-title">微信开发学习日记(三):6点经验</h2>
            <p class="blog-post-meta">2015年04月02日<a href="#">Mark</a></p>
            <p><strong>1.token验证和微信请求响应</strong><br/>token验证，用get方法。</p><p>&nbsp;微信请求入口，用psot方法。</p><p>&nbsp;一个微信公众号或者轻应用的入口，就是一个url，同一个url分为get和post。</p><p>&nbsp;get的时候，4个参数都不为空，必须校验。</p><p>&nbsp;post的时候，也要校验参数，只是不用输出echostr。<br/>post的时候，echostr可能为空，是否要检查echostr是否为空呢？既然不用输出，按说不需要校验。
            <strong>2.接口访问凭证access_token</strong><br/>每次获得一次凭证，有一定的过期时间。也就是说，可以缓存起来。<br/>暂时还没仔细去看代码。</p><p style="margin-top: 0px; margin-bottom: 15px;"><br/></p><p><strong>3.消息请求与响应</strong><br/>请求时的fromUserName-响应时的toUserName<br/>请求时的toUserName-响应时的fromUserName</p><p><strong>4.微信的接口不就是一个API嘛</strong></p><p>微信本身接口，也就那样，不是很难。</p><p>多年之前，对外提供接口就很流行了。API、函数、方法，都是一个鸟样，输入-处理-输出。</p><p style="margin-top: 0px; margin-bottom: 15px;"><br/></p><p><strong>5.复用与基础库</strong></p><p>按钮、菜单、XML和对象转换等很多都是可复用的基础库。</p><p>用了面向对象，看起来非常清楚。</p><p>不同的项目，完全可以重用。</p><p><strong>6.微信开发调试器</strong></p><p>&nbsp; 网上有不少，也有人自己写。<br/>我用的下面这个</p><p style="margin-top: 0px; margin-bottom: 15px;"><br/></p><p style="margin-top: 0px; margin-bottom: 15px;">http://www.jeecg.org/forum.php?mod=attachment&amp;aid=ODQ3fDUxOTUyNGM0fDE0MDU0MDc0NjF8NTUyN3wxODY1需要注意的是，要么在参数中带上完整的token等校验，要么加上特别的参数，比如devMode=true，避免校验。</p><p style="margin-top: 0px; margin-bottom: 15px;">我采用的是带上自己特定的参数，devMode=true这种方式。</p><p style="margin-top: 0px; margin-bottom: 15px;">public static boolean checkSignature(TokenCheck tokenCheck){</p><p>if(tokenCheck.getDevMode() != null){</p><p>return true;</p><p>}</p><p>return checkSignature(tokenCheck.getSignature(),tokenCheck.getTimestamp(),tokenCheck.getNonce());<br/>}小雷FansUnion-武汉九天鸟科技有限公司 创始人&amp;负责人</p><p><br/></p>


          </div><!-- /.blog-post -->

          <div class="blog-post">
            <h2 class="blog-post-title">Another blog post</h2>
            <p class="blog-post-meta">December 23, 2013 by <a href="#">Jacob</a></p>

            <p>Cum sociis natoque penatibus et magnis <a href="#">dis parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.</p>
            <blockquote>
              <p>Curabitur blandit tempus porttitor. <strong>Nullam quis risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
            </blockquote>
            <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
            <p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
          </div><!-- /.blog-post -->

          <div class="blog-post">
            <h2 class="blog-post-title">New feature</h2>
            <p class="blog-post-meta">December 14, 2013 by <a href="#">Chris</a></p>

            <p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
            <ul>
              <li>Praesent commodo cursus magna, vel scelerisque nisl consectetur et.</li>
              <li>Donec id elit non mi porta gravida at eget metus.</li>
              <li>Nulla vitae elit libero, a pharetra augue.</li>
            </ul>
            <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
            <p>Donec ullamcorper nulla non metus auctor fringilla. Nulla vitae elit libero, a pharetra augue.</p>
          </div><!-- /.blog-post -->

          <nav>
            <ul class="pager">
              <li><a href="#">Previous</a></li>
              <li><a href="#">Next</a></li>
            </ul>
          </nav>

        </div><!-- /.blog-main -->

        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
          <div class="sidebar-module sidebar-module-inset">
            <!-- <h4>关于</h4>
            <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
          </div> -->
          <div class="sidebar-module">
            <h4>时间线</h4>
            <ol class="list-unstyled">
              <li><a href="#">2015年3月</a></li>
              <li><a href="#">February 2014</a></li>
              <li><a href="#">January 2014</a></li>
              <li><a href="#">December 2013</a></li>
              <li><a href="#">November 2013</a></li>
              <li><a href="#">October 2013</a></li>
              <li><a href="#">September 2013</a></li>
              <li><a href="#">August 2013</a></li>
              <li><a href="#">July 2013</a></li>
              <li><a href="#">June 2013</a></li>
              <li><a href="#">May 2013</a></li>
              <li><a href="#">April 2013</a></li>
            </ol>
          </div>
          <div class="sidebar-module">
            <h4>分类</h4>
            <ol class="list-unstyled">
              <li><a href="#">java</a></li>
              <li><a href="#">python</a></li>
              <li><a href="#">Facebook</a></li>
            </ol>
          </div>
        </div><!-- /.blog-sidebar -->

      </div><!-- /.row -->

    </div><!-- /.container -->

    <footer class="blog-footer">
      <p>Blog template built for <a href="http://getbootstrap.com">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
      <p>
        <a href="#">Back to top</a>
      </p>
    </footer>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  </body>
</html>
