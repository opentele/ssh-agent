help:
	@IFS=$$'\n' ; \
	help_lines=(`fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//'`); \
	for help_line in $${help_lines[@]}; do \
	    IFS=$$'#' ; \
	    help_split=($$help_line) ; \
	    help_command=`echo $${help_split[0]} | sed -e 's/^ *//' -e 's/ *$$//'` ; \
	    help_info=`echo $${help_split[2]} | sed -e 's/^ *//' -e 's/ *$$//'` ; \
	    printf "%-30s %s\n" $$help_command $$help_info ; \
	done

SU:=$(shell id -un)

define _run_server
	java -jar --enable-preview daemon/build/libs/daemon-0.0.1-SNAPSHOT.jar --app.cron.main="0/3 * * * * ?" --app.cron.full.error="0 1 * * * ?"
endef

####### BUILD, TEST, LOCAL RUN
build-server: ## Builds the jar file
	./gradlew clean build -x test

setup-log-dir:
	-sudo mkdir /var/log/opentele-ssh-agent
	-sudo chown $(SU) /var/log/opentele-ssh-agent

run-server: build-server
	$(call _run_server)

run-server-without-background: build-server
	java -jar --enable-preview daemon/build/libs/daemon-0.0.1-SNAPSHOT.jar --app.cron.main="0 0 6 6 9 ? 2035"

test-server: build-server
	./gradlew unitTest

open-unit-test-results-daemon:
	open daemon/build/reports/tests/unitTest/index.html
#######


####### Tunnels
tunnel-server-debug-vagrant:
	ssh -p 2222 -i ~/.vagrant.d/insecure_private_key vagrant@127.0.0.1 -L 6031:localhost:6031
#######


####### SOURCE CONTROL
tag-release:
ifndef version
	$(error ERROR: version not provided.)
endif
	git tag -a v$(version) -m "version $(version)"
	git push origin --tags
#######


####### Deployment
deploy-to-vagrant-only:
	echo vagrant | pbcopy
	scp -P 2222 -i ~/.vagrant.d/insecure_private_key daemon/build/libs/daemon-0.0.1-SNAPSHOT.jar root@127.0.0.1:/root/source/

deploy-to-vagrant: build-server deploy-to-vagrant-only
#######
