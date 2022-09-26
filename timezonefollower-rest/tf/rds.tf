resource "aws_rds_cluster" "timezone_follower_rds" {
  cluster_identifier              = "timezonefollower"
  engine                          = "aurora-mysql"
  engine_version                  = "5.7.mysql_aurora.2.08.3"
  apply_immediately               = true
  enable_http_endpoint            = true
  database_name           = "timezone_follower"
  master_username         = var.timezone_follower_rds_cluster_username
  master_password         = var.timezone_follower_rds_cluster_password
  backup_retention_period = 1
  storage_encrypted = true
  engine_mode = "serverless"
  skip_final_snapshot = true

  scaling_configuration {
    auto_pause               = true
    max_capacity             = 2
    min_capacity             = 1
    seconds_until_auto_pause = 600
    timeout_action           = "ForceApplyCapacityChange"
  }
}