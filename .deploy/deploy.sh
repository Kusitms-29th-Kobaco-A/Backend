#! /bin/bash

green_application_port=0
green_application_name="green-application"
blue_application_name="blue-application"
temp_application_name="deprecated-application"

current_application_port=$(docker inspect --format='{{index .NetworkSettings.Ports "8080/tcp" 0 "HostPort"}}' ${blue_application_name})

echo "blue-application port is ${current_application_port}"

if [ "$current_application_port" -eq 8081 ]; then
        green_application_port=8082
else
        green_application_port=8081
fi

echo "green-application port is ${green_application_port}"

image_name="wendyjihyo/github-actions-kobaco"

echo $(docker pull ${image_name})
echo $(docker run -d -p ${green_application_port}:8080 --name ${green_application_name} --net application --env-file /home/ubuntu/.env  ${image_name})
echo $(docker image prune -f)


application_status="FAIL"

for i in {1..10}; do
        status=$(docker exec -it ${green_application_name} curl localhost:8079/actuator/health | grep -o '"status":"[^"]*' | awk -F ':"' '{print $2}')

        if [ "$status" == "UP" ]; then
                application_status="SUCCESS"
                echo ${application_status}
                break;
        else
                echo "${application_status}"
                echo "wait for application ready..."
                sleep 10
        fi
done

if [ "$application_staus" == "FAIL" ]; then
        echo "application unhealty"
        $(docker rm -f $green_application_name)
else
        echo "reload processing"
        echo $(docker rename $blue_application_name $temp_application_name)
        echo $(docker rename $green_application_name $blue_application_name)
        echo $(docker exec -i nginx-nginx-1 service nginx reload)
        echo $(docker rm -f $temp_application_name)
fi