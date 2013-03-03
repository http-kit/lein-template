# lein-template

A Leiningen 2.0 template for Clojure web programming (working in progress)


### How to start in dev

`./scripts/run`

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
