# lein-template

A Leiningen 2.0 template for Clojure web programming (working in progress)


# How to install

Add the following to your `~/.lein/profiles.clj`

```clj
{:user {:plugins [
                  [http-kit/lein-template "1.0.0-SNAPSHOT"]
                  ]}}
```

Use it:

```sh
lein new http-kit your.project.name
```

For example:

```sh
lein new http-kit clj_web && cd clj_web

=>
├── README.md
├── project.clj
├── public
│   ├── css
│   │   ├── bootstrap-responsive.css
│   │   ├── bootstrap.css
│   │   └── style.css
│   ├── img
│   │   ├── glyphicons-halflings-white.png
│   │   └── glyphicons-halflings.png
│   └── js
│       └── lib
│           ├── bootstrap.js
│           └── jquery-1.9.1.js
├── scripts
│   └── run
├── src
│   ├── clj_web
│   │   ├── config.clj
│   │   ├── handlers
│   │   │   ├── api.clj
│   │   │   └── app.clj
│   │   ├── main.clj
│   │   ├── middleware.clj
│   │   ├── routes.clj
│   │   └── tmpls.clj
│   ├── logback.xml
│   └── templates
│       ├── landing.tpl
│       └── partials
│           └── header.tpl
└── test
    ├── clj_web
    │   ├── app_test.clj
    │   └── test_common.clj
    └── logback-test.xml

```

### Start the a server for local testing

```sh
./scripts/run
```

### Run unit test

```sh
lein test
```

### Template (Mustache)

Mustache is easy to learn. I've used it in severial projects, and quite happy with it.
files in src/templates, a Clojure function is created according to tempalte name, eg:

`landing.tpl` => `lein-template.tmpl/landing`

### Deploy

1. `lein uberjar` create a standalone executable jar file in the target directory
2. copy the jar and the `public` directory to your production server
3. ```sh
java -jar target/lein-template-standalone.jar --profile prod --port 8000
```

## License

Copyright © 2013 Feng Shen

Distributed under the Eclipse Public License, the same as Clojure.
