resource "aws_ecr_repository" "timezone_follower_repo" {
  name                 = "timezone_follower"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}

resource "aws_ecr_lifecycle_policy" "timezone_follower_repo_policy" {
  repository = aws_ecr_repository.timezone_follower_repo.name

  policy = <<EOF
{
    "rules": [
        {
            "rulePriority": 1,
            "selection": {
                "tagStatus": "any",
                "countType": "imageCountMoreThan",
                "countNumber": 16
            },
            "action": {
                "type": "expire"
            }
        }
    ]
}
EOF
}