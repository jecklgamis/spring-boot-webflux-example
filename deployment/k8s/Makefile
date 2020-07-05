APP_NAME:=spring-boot-webflux-example
default:
	cat ./Makefile
apply:
	kubectl apply -f deployment.yaml --record
	kubectl get all
delete:
	kubectl delete deployment,service $(APP_NAME)
get-all:
	kubectl get pods -l app=$(APP_NAME)
describe-all:
	kubectl describe pods -l app=$(APP_NAME)
	kubectl describe service $(APP_NAME)
	kubectl describe deploy $(APP_NAME)
rollout-status:
	kubectl rollout status deploy $(APP_NAME)
rollout-history:
	kubectl rollout history deploy $(APP_NAME)
