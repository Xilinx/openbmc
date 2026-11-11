# meta-amd-adaptive-socs-openbmc

Collection of layers to enable AMD Adaptive SoC's openbmc bsp cache metadata.

* **meta-amd-adaptive-socs-openbmc**: layer containing AMD Adaptive SoC's openbmc
features. This layer enables AMD adaptive socs metadata features such as recipes
for u-boot configuration fragments, kernel configuration fragments, series
configuration compiler(.scc) files etc,. and common image features etc.

* **meta-amd-zcu102-openbmc**: layer containing the zcu102-openbmc machine
configuration for the AMD Zynq UltraScale+ MPSoC ZCU102 evaluation board such
as machine configuration, multiconfig, system device tree, machineyaml, wks and
default build template files.

* **meta-amd-zcu104-openbmc**: layer containing the zcu104-openbmc machine
configuration for the AMD Zynq UltraScale+ MPSoC ZCU104 evaluation board such
as machine configuration, multiconfig, system device tree, machineyaml, wks and
default build template files.

* **meta-amd-zcu106-openbmc**: layer containing the zcu106-openbmc machine
configuration for the AMD Zynq UltraScale+ MPSoC ZCU106 evaluation board such
as machine configuration, multiconfig, system device tree, machineyaml, wks and
default build template files.

> **Note:** Additional information on AMD Adaptive SoC's and FPGA's can be found at:
	https://www.amd.com/en/products/adaptive-socs-and-fpgas.html

Please see the respective READMEs and docs in the layer subdirectories.

## Build Instructions

See [README.building.md](README.building.md) for instructions on setting up the
build host and building OpenBMC images for AMD Adaptive SoCs evaluation boards.

## Dependencies

This layer depends on:

	URI: https://git.openembedded.org/openembedded-core
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
	branch: wrynose or AMD release version (e.g. rel-v2026.2)

The meta-amd-zcu102-openbmc, meta-amd-zcu104-openbmc and meta-amd-zcu106-openbmc
sub layers additionally depend on:

	URI:
        https://github.com/Xilinx/meta-amd-adaptive-socs (development and AMD release)
	layers: meta-amd-adaptive-socs-bsp
	branch: wrynose or AMD release version (e.g. rel-v2026.2)
