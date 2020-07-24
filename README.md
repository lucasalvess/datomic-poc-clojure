# datomic-clojure

Test clojure features 

## Datomic free configuration

#### Download dependencies
In the folder `/devops/lib` run:

```shell script
./maven-install
```
#### Run Transactor (datomic server)
In the folder `/devops/docker` run:

```shell script
docker-compose up --build
```
exit:
```
Launching with Java options -server -Xms1g -Xmx1g -XX:+UseG1GC -XX:MaxGCPauseMillis=50
Starting datomic:dev://localhost:4334/<DB-NAME>, storing data in: data ...
System started datomic:dev://localhost:4334/<DB-NAME>, storing data in: data
```

## License

Copyright © 2020 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
