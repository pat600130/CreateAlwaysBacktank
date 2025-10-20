git add .
if ($args.Length -eq 0) {
    Write-Host "Please provide a commit message."
    exit 1
}
git commit -m $args[0]
git push 