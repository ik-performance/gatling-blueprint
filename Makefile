.PHONY: pre-commit changelog docker

SHELL = /bin/bash -o pipefail
PROJECT_PATH ?= $(shell 'pwd')
CURRENT_TIME ?= $(shell date +%Y-%m-%d--%H-%M)

DOCKER_IMAGE ?= cloudkats/gatling:3.3.1
SIMULATION ?= initial

green = $(shell echo -e '\x1b[32;01m$1\x1b[0m')
yellow = $(shell echo -e '\x1b[33;01m$1\x1b[0m')
red = $(shell echo -e '\x1b[33;31m$1\x1b[0m')

help:
	@grep -E '^[/\a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

init: ## Commit hooks setup
	@pre-commit install
	@pre-commit gc
	@pre-commit autoupdate

validate: ## Validate with pre-commit hooks
	@pre-commit run --all-files

change: ## Update changelog
	git-chglog -o CHANGELOG.md --next-tag `semtag final -s minor -o`

docker/inspect: ## Inspect container
	@docker run --rm -it \
		-v ${PROJECT_PATH}/conf:/opt/gatling/conf \
		-v ${PROJECT_PATH}/src/gatling:/opt/gatling/user-files \
		-v ${PROJECT_PATH}/results:/opt/gatling/results \
		$(DOCKER_IMAGE) /bin/bash

reports: ## Awailable Reports
	@ls -ld results/* | cut -d ' ' -f12

clean/results: ## Clean results folder
	@rm -rf $$(ls -ld results/* | cut -d ' ' -f12)

run/simulation: ## Gatling run scenario 'make simulation=initial'
	@docker run --rm -it \
		-v ${PROJECT_PATH}/conf:/opt/gatling/conf \
		-v ${PROJECT_PATH}/src/gatling:/opt/gatling/user-files \
		-v ${PROJECT_PATH}/results:/opt/gatling/results \
		$(DOCKER_IMAGE) \
		bin/gatling.sh \
		--simulation $(SIMULATION) \
		--results-folder /opt/gatling/results/$(SIMULATION)-$(CURRENT_TIME)

choose/simulation: ## Show all simulations
	@docker run --rm -it \
		-v ${PROJECT_PATH}/conf:/opt/gatling/conf \
		-v ${PROJECT_PATH}/src/gatling:/opt/gatling/user-files \
		-v ${PROJECT_PATH}/results:/opt/gatling/results \
		$(DOCKER_IMAGE) \
		bin/gatling.sh \
		--results-folder /opt/gatling/results/$(CURRENT_TIME)
