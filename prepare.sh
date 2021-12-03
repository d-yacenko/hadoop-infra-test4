./gradlew jar
docker build -t dyacenko/hadoop-infra-test4 .
docker login
docker push dyacenko/hadoop-infra-test4
#kubectl run -it --rm hadoop-evnp --image=dyacenko/hadoop-infra-test4
#kubectl exec dyacenko/hadoop-infra-test4 -- curl -s localhost:8080
