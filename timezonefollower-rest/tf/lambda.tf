resource "aws_iam_role" "timezone_follower_iam_lambda" {
  name = "timezone_follower_iam_lambda"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_lambda_function" "timezone_follower_lambda" {
  function_name = "timezone_follower"
  role          = aws_iam_role.timezone_follower_iam_lambda.arn
  image_uri     = format("%s:%s", aws_ecr_repository.timezone_follower_repo.repository_url, "latest")
  memory_size   = 128
  package_type  = "Image"
  timeout       = 10

  depends_on = [
    aws_iam_role_policy_attachment.timezone_follower_lambda_logs_policy_attachment,
    aws_cloudwatch_log_group.timezone_follower_lambda_logs,
  ]
}

resource "aws_cloudwatch_log_group" "timezone_follower_lambda_logs" {
  name              = "/aws/lambda/timezone_follower"
  retention_in_days = 7
}

resource "aws_iam_policy" "timezone_follower_lambda_logs_policy" {
  name        = "timezone_follower_lambda_logs_policy"
  path        = "/"

  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:PutLogEvents"
      ],
      "Resource": "arn:aws:logs:*:*:*",
      "Effect": "Allow"
    }
  ]
}
EOF
}

resource "aws_iam_role_policy_attachment" "timezone_follower_lambda_logs_policy_attachment" {
  role       = aws_iam_role.timezone_follower_iam_lambda.name
  policy_arn = aws_iam_policy.timezone_follower_lambda_logs_policy.arn
}
