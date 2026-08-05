# meta-amd-adaptive-socs-openbmc

Collection of layers to enable AMD Adaptive SoC's openbmc bsp cache metadata.

* **meta-amd-adaptive-socs-openbmc**: layer containing AMD Adaptive SoC's openbmc
features. This layer enables AMD adaptive socs metadata features such as recipes
for u-boot configuration fragments, kernel configuration fragments, series
configuration compiler(.scc) files etc,. and common image features etc.

> **Note:** Additional information on AMD Adaptive SoC's and FPGA's can be found at:
	https://www.amd.com/en/products/adaptive-socs-and-fpgas.html

Please see the respective READMEs and docs in the layer subdirectories.

## Dependencies

This layer depends on:

	URI: https://git.yoctoproject.org/poky
	layers: meta
	branch: wrynose

	URI: https://git.openembedded.org/meta-openembedded
	layers: meta-oe, meta-python, meta-networking.
	branch: wrynose

	URI: https://git.yoctoproject.org/meta-arm
	layers: meta-arm, meta-arm-toolchain
	branch: wrynose

	URI: https://github.com/openbmc/openbmc
	layers: meta-phospher
	branch: wrynose

	URI:
        https://git.yoctoproject.org/meta-xilinx (official version)
        https://github.com/Xilinx/meta-xilinx (development and AMD release)
	layers: meta-xilinx-core, meta-xilinx-standalone, meta-xilinx-standalone-sdt,
	        meta-microblaze.
	branch: wrynose
