.PHONY: pre-commit changelog release

SHELL = /bin/bash -o pipefail
DOCKER_IMAGE ?= cloudkats/gatling:3.3.1
PROJECT_PATH ?= $(shell 'pwd')

help:
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

init: ## Commit hooks setup
	@pre-commit install
	@pre-commit gc
	@pre-commit autoupdate

validate: ## Validate with pre-commit hooks
	@pre-commit run --all-files

change: ## Update changelog
	git-chglog -o CHANGELOG.md --next-tag `semtag final -s minor -o`

simulation: ## Gatling run scenario
	@docker run --rm -it \
		-v ${PROJECT_PATH}/conf:/opt/gatling/conf \
		-v ${PROJECT_PATH}/src/gatling:/opt/gatling/user-files \
		-v ${PROJECT_PATH}/results:/opt/gatling/results \
		$(DOCKER_IMAGE) \
		bin/gatling.sh \
		--simulation SimpleSimulation