# Contributing

The general process of contribution is as follows

1. Fork this repository
2. Create a branch and make changes
3. `git pull --rebase` anyone-can-code/space-fortress
4. Create a pull request
5. We'll ask for changes, or merge the pull request!


## Building

The project should automatically build fine in most IDEs, but for command-line users, build.sh can compile it. I still need to get build.sh to generate a .jar; for now it only compiles the files.

When distributing, distribute either a jar or a native application created from a jar using GraalVM.
