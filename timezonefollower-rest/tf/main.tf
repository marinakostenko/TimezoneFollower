terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region = "us-west-1"
}

variable "timezone_follower_rds_cluster_username" {
  type = string
  sensitive = true
}

variable "timezone_follower_rds_cluster_password" {
  type = string
  sensitive = true
}