# shipping-cljs

generated using Luminus version "3.62"

FIXME

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein run 

## Electron Dev

Start the server

```
lein repl
```

Start figwheel

```
lein figwheel
```

Build the electron entrypoint.

```
lein cljsbuild once electron
```

Start electron

```
electron .
```

## License

Copyright © 2020 FIXME
