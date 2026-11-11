# Building AMD Adaptive SoCs OpenBMC Images

This document describes how to prepare a build host and build OpenBMC images
for AMD Adaptive SoCs evaluation boards using the `meta-amd-adaptive-socs-openbmc`
layer and the required upstream layers.

## Prerequisites

1. See [Preparing Build Host](https://docs.yoctoproject.org/singleindex.html#preparing-the-build-host)
   documentation.
2. Configure the git settings before you run the **repo** commands.
```
$ git config --global user.email "you@example.com"
$ git config --global user.name "Your Name"
```
3. Make sure the build host shell is `bash`, not `csh` or `dash`.
4. A basic understanding of the Yocto build system is assumed. Please consult
   the documentation for further information: https://docs.yoctoproject.org/

## Build Instructions

1. Download and install the Repo tool (if it wasn't installed in a previous
   step). Note that if you have repo installed through a package manager, it
   should be removed first as it is likely out of date and will cause issues.
```
$ curl https://storage.googleapis.com/git-repo-downloads/repo > repo
$ chmod a+x repo
$ mv repo ~/bin/
$ PATH=~/bin:$PATH
$ repo --help
```

2. Initialize a Repo client.
   * Create the `<release_version>` project directory, for example `amd-wrynose`
     depending on the release version.
```
$ mkdir -p yocto/<release_version>
$ cd yocto/<release_version>
```
   * Clone the Yocto meta layer sources using the yocto manifest as shown below.
     A successful initialization will end with a message stating that Repo is
     initialized in your working directory. Your directory should now contain a
     `.repo` directory where repo control files such as the manifest are stored,
     but you should not need to touch this directory. To learn more about repo,
     see https://source.android.com/setup/develop/repo
```
$ repo init -u https://github.com/Xilinx/yocto-manifests.git -b amd-wrynose -m default-obmc.xml
```

3. Fetch all the repositories.
```
$ repo sync
```

4. Start a branch for development starting from the revision specified in the
   manifest. This is an optional step.
```
$ repo start <branch_name> --all
```

5. Initialize a build environment using the OpenBMC `setup` script. Once the
   environment is initialized, `bblayers.conf` and `local.conf` are set from the
   `meta-amd-adaptive-socs-openbmc/meta-amd-<board>-openbmc` templates.

   After `repo sync`, the `sources/openbmc/meta-amd-adaptive-socs-openbmc`
   directory contains the following layers and symlinks:
```
meta-amd-adaptive-socs-openbmc/
├── common
├── conf
├── COPYING.MIT
├── meta-amd-adaptive-socs-bsp -> ../../meta-amd-adaptive-socs/meta-amd-adaptive-socs-bsp
├── meta-amd-zcu102-openbmc
├── meta-amd-zcu104-openbmc
├── meta-amd-zcu106-openbmc
├── meta-xilinx -> ../../meta-xilinx
├── OWNERS
├── README.building.md
├── README.machine.generation.md
├── README.md
└── recipes-phosphor
```
   Running `setup` without arguments lists all the supported target machines.
   The AMD Adaptive SoCs machines are `zcu102-openbmc`, `zcu104-openbmc` and
   `zcu106-openbmc`.
```
$ cd sources/openbmc
$ . setup
Target machine must be specified. Use one of:
...
zcu102-openbmc
zcu104-openbmc
zcu106-openbmc
$ . setup zcu106-openbmc
```

6. For an NFS build host, modify `build/zcu106-openbmc/conf/local.conf` and add
   the `TMPDIR` path as shown below. On local storage `TMPDIR` will be set to
   `build/zcu106-openbmc/tmp`.
```
TMPDIR = "/tmp/<user-name>/yocto/<release_version>/build"
```
> **Note:** Depending on the build configuration, several 10s of GB of storage
> space may be required in the `TMPDIR`. Please make sure you have plenty of
> storage space available. Alternatively, to save disk space, you can add the
> below to your `build/zcu106-openbmc/conf/local.conf`. This option removes the
> work directory after the build and only keeps the logs around.
```
INHERIT += "rm_work"
```

7. Build the `qemu-helper-native` package to set up QEMU network tap devices.
   This is an optional step.
```
$ bitbake qemu-helper-native
```
   Manually configure a tap interface for your build system. As root, run the
   command below, which should generate a list of tap devices. Once the tap
   interfaces are successfully created, you should be able to see all the
   interfaces by running the `ifconfig` command.
```
$ sudo <path-to-openbmc>/scripts/runqemu-gen-tapdevs $(id -g $USER) 4
```

8. Build the `obmc-phosphor-image` target image.
```
$ bitbake obmc-phosphor-image
```

9. Launch the `runqemu` script to boot the images using QEMU.
> **Note:** Use `slirp` option if you don't have sudo permissions and tap devices
> are not enabled on your build host (see step 7).

   * Without slirp (with tap devices)
```
$ runqemu nographic
```

   * With slirp (no tap devices)
```
$ runqemu nographic slirp
```
> **Note:** To terminate QEMU, usually the command is ctrl-a x

> **Note:** Default username and password for OpenBMC images:
> * **Username:** `root`
> * **Password:** `0penBmc`

10. For hardware boot, the WIC images are available in the deploy directory
    shown below. Flash the `wic.xz` image to an SD card and boot the board in
    SD boot mode.
```
$ ls ${TMPDIR}/deploy/images/zcu106-openbmc/obmc-phosphor-image-zcu106-openbmc-<timestamp>.wic.xz
```
