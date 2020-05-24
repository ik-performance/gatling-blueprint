# gatlint-blueprint

Starter/Blueprint project for writing performance tests with Gatling, using Gradle as buildtool.

- [Documentation](https://gatling.io/docs/current/)
- [Cheet Sheet](https://gatling.io/docs/current/cheat-sheet/)
- [Reports](https://gatling.io/docs/current/general/reports/)

[![](https://img.shields.io/github/license/ik-performance/gatling-blueprint)](https://github.com/ik-performance/gatling-blueprint)
![](https://img.shields.io/github/v/tag/ik-performance/gatling-blueprint)
![](https://img.shields.io/issues/github/ik-performance/gatling-blueprint)
![](https://img.shields.io/github/issues/ik-performance/gatling-blueprint)
![](https://img.shields.io/github/issues-closed/ik-performance/gatling-blueprint)
[![](https://img.shields.io/github/languages/code-size/ik-performance/gatling-blueprint)](https://github.com/ik-performance/gatling-blueprint)
[![](https://img.shields.io/github/repo-size/ik-performance/gatling-blueprint)](https://github.com/ik-performance/gatling-blueprint)
![](https://img.shields.io/github/languages/top/ik-performance/gatling-blueprint?color=green&logo=terraform&logoColor=blue)
![](https://img.shields.io/github/commit-activity/m/ik-performance/gatling-blueprint)
![](https://img.shields.io/github/contributors/ik-performance/gatling-blueprint)
![](https://img.shields.io/github/last-commit/ik-performance/gatling-blueprint)

- [gatlint-blueprint](#gatlint-blueprint)
  * [Running locally](#running-locally)
    + [Gatling DSL](#gatling-dsl)
    + [Scenario's](#scenario-s)
    + [Simulations](#simulations)
  * [Mocking](#mocking)
    + [Data](#data)
    + [API](#api)
  * [Alternative Examples](#alternative-examples)
  * [Targets](#targets)
  * [:memo: Guidelines](#-memo--guidelines)
  * [License](#license)
  * [How to Contribute](#how-to-contribute)
- [Authors](#authors)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

## Running locally

### Gatling DSL
Gatling provides its own DSL to elegantly build tests. A few of the most important concepts are listed below. For more information, see the Gatling documentation: https://gatling.io/docs/current/

### Scenario's
A scenario contains at least one request. Typically, this is a HTTP request.

### Simulations
A simulation can be seen as the actual test. It determines what scenario's should be run, and how many users should be simulated over the scenario's.

## Mocking

### Data

- https://mockaroo.com/

### API

- https://mockaroo.com/
- https://jsonplaceholder.typicode.com
- https://test-api.k6.io/

## Alternative Examples

- https://qautomation.blog/category/gatling/
- https://github.com/flood-io/demo-gatling.git
- https://github.com/Shashikant86/Docker-Gatling
- https://github.com/pedroxs/gatling-docker
- https://github.com/dblooman/gatling-docker
- https://github.com/biswajit-713/gatling-gradle-demo
- https://github.com/eswets/gatling-gradle-starter
- https://github.com/antusus/gatling-posts.git
- https://github.com/jecklgamis/gatling-test-example

## Targets

<!-- START makefile-doc -->
```
$ make help 
init                           Commit hooks setup
validate                       Validate with pre-commit hooks
change                         Update changelog
docker/inspect                 Inspect container
reports                        Awailable Reports
clean/results                  Clean results folder
run/simulation                 Gatling run scenario 'make simulation=initial'
choose/simulation              Show all simulations 
```
<!-- END makefile-doc -->

## :memo: Guidelines

 - :memo: Use a succinct title and description.
 - :bug: Bugs & feature requests can be be opened
 - :signal_strength: Support questions are better asked on [Stack Overflow](https://stackoverflow.com/)
 - :blush: Be nice, civil and polite ([as always](http://contributor-covenant.org/version/1/4/)).

## License

Copyright 2019 Ivan Katliarhcuk

MIT Licensed. See [LICENSE](./LICENSE) for full details.

## How to Contribute

Submit a pull request

# Authors

Currently maintained by [Ivan Katliarchuk](https://github.com/ivankatliarchuk) and these [awesome contributors](https://github.com/terraform-module/terraform-module-blueprint/graphs/contributors).

[![ForTheBadge uses-git](http://ForTheBadge.com/images/badges/uses-git.svg)](https://GitHub.com/)
