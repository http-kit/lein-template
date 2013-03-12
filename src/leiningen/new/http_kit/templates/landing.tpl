<!DOCTYPE html>
<html>
  <!-- http://mustache.github.com/mustache.5.html -->
  <head>
    <!-- partial is just like copy and paste the template here -->
    {{>partials/header}}
  </head>
  <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">{{sanitized-ns}}</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">

      <h1>Bootstrap starter template</h1>
      <p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>

      <p>Your user agent is : {{ user-agent }}</p>

      <h3>Example list</h3>
      <ul>
        {{#list}}
          <li>{{ . }}</li>
        {{/list}}
      </ul>

    </div>
    <script src="/static/js/lib/jquery-1.9.1.js"></script>
    <script src="/static/js/lib/bootstrap.js"></script>
  </body>
</html>
