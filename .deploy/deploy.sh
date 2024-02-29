#! /bin/sh

current_application_port=$(expr $application_port)

echo ${current_application_port}

green_application_port=0
green_application_name="green-application"
blue_application_name="blue-application"
temp_application_name="deprecated-application"

if [ "$current_application_port" -eq 8081 ]; then
        green_application_port=8082
else
        green_application_port=8081
fi

echo ${green_application_port}


echo $(docker pull wendyjihyo/github-actions-kobaco)

cmd=$(docker run -d -p ${green_application_port}:8080 --name ${green_application_name} --net application --env-file /home/ubuntu/.env  wendyjihyo/github-actions-kobaco)

echo $(docker image prune -f)

echo ${cmd}

# 실행 되는지 확인 -> 확인이 health 채크가 완료되면 blue rename to deprecated-application -> green rename to blue -> nginx reload

application_status="FAIL"

for i in {1..10}; do
        cmd=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:${green_application_port}/health)

        echo ${cmd}

        if [ "$cmd" -eq 202 ]; then
                application_status="SUCCESS"
                echo ${application_status}
                break;
        else
                echo "FAIL"
                sleep 10
        fi
done

if [ "$application_staus" == "FAIL" ]; then
        echo "application unhealty"
        $(docker rm -f $green_application_name)
else
        echo "reload processing"
        echo $(docker image prune -f)
        export application_port=${green_application_port}
        echo $(docker rename $blue_application_name $temp_application_name)
        echo $(docker rename $green_application_name $blue_application_name)
        echo $(docker exec -it nginx-nginx-1 service nginx reload)
        echo $(docker rm -f $temp_application_name)
fi