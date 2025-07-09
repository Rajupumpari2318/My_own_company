#locals {
# operations_api_repo_url = "${var.central_ecr_account}.dkr.ecr.${var.region}.amazonaws.com/${var.operations_api_repo_name}"
# cw_agent_repo_url = "${var.central_ecr_account}.dkr.ecr.${var.region}.amazonaws.com/${var.cw_agent_repo_name}"
#}
#
#resource "aws_ecs_task_definition" "operations_portals_api" {
# family = "operations-portals-api"
# requires_compatibilities = ["FARGATE"]
# network_mode = "awsvpc"
# cpu = "256"
# memory = "512"
# execution_role_arn = aws_iam_role.operations-execution-role.arn
# task_role_arn = aws_iam_role.operations_ecs_task_role.arn
#
# container_definitions = <<DEFINITION
#[
# {
# "name": "operations-portals-api",
# "image": "${local.operations_api_repo_url}:${var.namespace}",
# "essential": true,
# "portMappings": [
# {
# "containerPort": 5000,
# "hostPort": 5000,
# "protocol": "tcp"
# }
# ],
# "command": [],
# "environment": [
# {
# "name": "ARM_TENANT_ID",
# "value": "${var.arm_tenant_id}"
# },
# {
# "name": "VIOLATIONS_INDEX",
# "value": "${var.violations_index}"
# },
# {
# "name": "CACHE_LOADER_HOSTNAME",
# "value": ""
# },
# {
# "name": "LOG_LEVEL",
# "value": "INFO"
# },
# {
# "name": "LAUNCHPAD_API_HOST",
# "value": "${var.launchpad_api_host}"
# },
# {
# "name": "FLASK_CONFIG",
# "value": "${var.flask_config}"
# },
# {
# "name": "BUSINESS_SEGMENTS_CONFIG_FILE",
# "value": "${var.business_segments_config_file}"
# },
# {
# "name": "REDIS_HOSTNAME",
# "value": "${aws_elasticache_replication_group.advisor.primary_endpoint_address}"
# },
# {
# "name": "REDIS_PORT",
# "value": "${var.redis_port}"
# },
# {
# "name": "REDIS_DB",
# "value": "${var.redis_db}"
# },
# {
# "name": "VIOLATIONS_STATS_INDEX",
# "value": "${var.violations_stats_index}"
# },
# {
# "name": "CLOUD_NATIVE_ADMIN_GROUP_OBJ_ID",
# "value": "${var.cloud_native_admin_group_obj_id}"
# },
# {
# "name": "VULNERABILITIES_INDEX",
# "value": "${var.vulnerabilities_index}"
# },
# {
# "name": "HTTPS_PROXY",
# "value": "${module.egress_proxy.url}"
# },
# {
# "name": "HCC_FORECAST_SCHEMA",
# "value": "0.0.1"
# },
# {
# "name": "DYNAMODB_TABLE_NAME",
# "value": "${local.cost_management_dynamodb_table_name}"
# },
# {
# "name": "CORS_ALLOWED_HOSTS",
# "value": "${var.cors_allowed_hosts}"
# }
# ],
# "secrets" : [
# {
# "name": "ELASTIC_SEARCH_HOST",
# "valueFrom": "${data.aws_ssm_parameter.elastic_search_host.arn}"
# },
# {
# "name": "ELASTIC_SEARCH_USERNAME",
# "valueFrom": "${data.aws_ssm_parameter.cb_user_access_key.arn}"
# },
# {
# "name": "ELASTIC_SEARCH_PASSWORD",
# "valueFrom": "${data.aws_ssm_parameter.cb_user_secret_key.arn}"
# },
# {
# "name": "REDLOCK_HOST",
# "valueFrom": "${data.aws_ssm_parameter.rl_host_url.arn}"
# },
# {
# "name": "REDLOCK_USERNAME",
# "valueFrom": "${data.aws_ssm_parameter.rl_username.arn}"
# },
# {
# "name": "REDLOCK_PASSWORD",
# "valueFrom": "${data.aws_ssm_parameter.rl_password.arn}"
# },
# {
# "name": "REDLOCK_US_HOST",
# "valueFrom": "${data.aws_ssm_parameter.rl_host_url.arn}"
# },
# {
# "name": "REDLOCK_US_USERNAME",
# "valueFrom": "${data.aws_ssm_parameter.rl_username.arn}"
# },
# {
# "name": "REDLOCK_US_PASSWORD",
# "valueFrom": "${data.aws_ssm_parameter.rl_password.arn}"
# },
# {
# "name": "REDLOCK_UK_HOST",
# "valueFrom": "${data.aws_ssm_parameter.rl_uk_host_url.arn}"
# },
# {
# "name": "REDLOCK_UK_USERNAME",
# "valueFrom": "${data.aws_ssm_parameter.rl_uk_username.arn}"
# },
# {
# "name": "REDLOCK_UK_PASSWORD",
# "valueFrom": "${data.aws_ssm_parameter.rl_uk_password.arn}"
# },
# {
# "name": "JWT_SECRET",
# "valueFrom": "${data.aws_ssm_parameter.lp_api_jwt_secret.arn}"
# },
# {
# "name": "LAUNCHPAD_API_BASIC_AUTH_USERNAME",
# "valueFrom": "${data.aws_ssm_parameter.lp_api_read_username.arn}"
# },
# {
# "name": "LAUNCHPAD_API_BASIC_AUTH_PASSWORD",
# "valueFrom": "${data.aws_ssm_parameter.lp_api_read_password.arn}"
# },
# {
# "name": "SP_MGR_CLIENT_ID",
# "valueFrom": "${data.aws_ssm_parameter.sp_mgr_client_id.arn}"
# },
# {
# "name": "SP_MGR_CLIENT_SECRET",
# "valueFrom": "${data.aws_ssm_parameter.sp_mgr_client_secret.arn}"
# },
# {
# "name": "SP_MGMT_CLIENT_ID",
# "valueFrom": "${data.aws_ssm_parameter.sp_mgmt_mgr_client_id.arn}"
# },
# {
# "name": "SP_MGMT_CLIENT_SECRET",
# "valueFrom": "${data.aws_ssm_parameter.sp_mgmt_mgr_client_secret.arn}"
# },
# {
# "name": "REDIS_SECRET",
# "valueFrom": "${aws_ssm_parameter.redis_secert.arn}"
# },
# {
# "name": "CAT_API_HOST",
# "valueFrom": "${data.aws_ssm_parameter.cat_api_host_url.arn}"
# },
# {
# "name": "CAT_API_BASIC_AUTH_USERNAME",
# "valueFrom": "${data.aws_ssm_parameter.cat_api_client.arn}"
# },
# {
# "name": "CAT_API_BASIC_AUTH_PASSWORD",
# "valueFrom": "${data.aws_ssm_parameter.cat_api_secret.arn}"
# }
# ],
# "logConfiguration": {
# "logDriver": "awslogs",
# "options": {
# "awslogs-group": "/aws/ecs/${var.team}-core-services-cluster",
# "awslogs-region": "us-east-1",
# "awslogs-stream-prefix": "server-logs"
# }
# }
# }
#]
#DEFINITION
#}
#
#resource "aws_ecs_task_definition" "ecs_cw_agent_taskdef" {
# family = "cwagent-prometheus-${var.cluster}-${local.launch_type}-awsvpc"
# requires_compatibilities = ["FARGATE"]
# network_mode = "awsvpc"
# cpu = 256
# memory = 512
# task_role_arn = aws_iam_role.cw_agent_ecs_task_role.arn
# execution_role_arn = aws_iam_role.cw_agent_ecs_execution_role.arn
#
# container_definitions = <<DEFINITION
# [
# {
# "name": "cwagent-prometheus-ecs-FARGATE-awsvpc",
# "image": "${local.cw_agent_repo_url}:${var.namespace}",
# "requires_compatibilities": ["FARGATE"],
# "essential": true,
# "portMappings": [
# ],
# "logConfiguration": {
# "logDriver": "awslogs",
# "options": {
# "awslogs-create-group": "True",
# "awslogs-group": "/aws/ecs/${var.team}-observability-cluster",
# "awslogs-region": "${var.region}",
# "awslogs-stream-prefix": "ecs"
# }
# },
# "secrets": [
# { "name": "PROMETHEUS_CONFIG_CONTENT", "valueFrom": "AmazonCloudWatch-PrometheusConfigName-${var.cluster}-FARGATE-awsvpc" },
# { "name": "CW_CONFIG_CONTENT", "valueFrom": "AmazonCloudWatch-CWAgentConfig-${var.cluster}-FARGATE-awsvpc" }
# ]
# }
# ]
#DEFINITION
#
# runtime_platform {
# operating_system_family = "LINUX"
# cpu_architecture = "X86_64"
# }
#}
#
#resource "aws_ecs_service" "operations-portal-api" {
# name = "cc-operations-portal-api"
# cluster = data.aws_ecs_cluster.ecs_cluster.id
# task_definition = aws_ecs_task_definition.operations_portals_api.arn
# desired_count = var.namespace == "prod" ? 3 : 1
# deployment_minimum_healthy_percent = 100
# deployment_maximum_percent = 200
#
# network_configuration {
# security_groups = [aws_security_group.operations-app.id]
# subnets = module.additional_subnets.private_subnet_ids
# assign_public_ip = true
#
# }
# launch_type = "FARGATE"
#
# service_registries {
# registry_arn = aws_service_discovery_service.operations-portal-api.arn
# }
#
# load_balancer {
# target_group_arn = aws_lb_target_group.ecs-operations-portal-api.arn
# container_name = "operations-portals-api"
# container_port = var.flask_app_port
# }
#}
#
#resource "aws_ecs_service" "ecs_cw_agent_service" {
# name = "cwagent-prometheus-replica-service-${local.launch_type}-awsvpc"
# cluster = data.aws_ecs_cluster.ecs_cluster.id
# launch_type = local.launch_type
# task_definition = aws_ecs_task_definition.ecs_cw_agent_taskdef.arn
# desired_count = var.namespace == "prod" ? 3 : 1
# deployment_minimum_healthy_percent = 100
# deployment_maximum_percent = 200
#
# network_configuration {
# subnets = module.additional_subnets.private_subnet_ids
# security_groups = [aws_security_group.prometheus-scraper.id]
# assign_public_ip = true
# }
#}
#
#resource "aws_cloudwatch_log_group" "core_services_log_group" {
# name = "/aws/ecs/${var.team}-core-services-cluster"
# retention_in_days = var.retention_in_days
# tags = merge(local.global_tags, tomap({ "Functionality" = "CoreServices" }))
#}
#
#resource "aws_cloudwatch_log_group" "observability_cluster_log_group" {
# name = "/aws/ecs/${var.team}-observability-cluster"
# retention_in_days = var.retention_in_days
# tags = merge(local.global_tags, tomap({ "Functionality" = "Observability" }))
#}
