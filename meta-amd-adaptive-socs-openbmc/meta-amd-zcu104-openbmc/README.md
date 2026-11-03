# meta-amd-zcu104-openbmc

This layer enables the AMD Adaptive SoC's bsp features for zcu104 evaluation
board openbmc system device tree build metadata such as machine configurations,
multiconfig, system device tree and machineyaml files.

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
	URI:
	 https://github.com/Xilinx/openbmc (development and AMD release)
	layers: meta-phosphor, meta-amd-adaptive-socs-openbmc
	branch: amd-wrynose

	URI:
	 https://git.yoctoproject.org/meta-xilinx (official version)
	 https://github.com/Xilinx/meta-xilinx (development and AMD release)
	layers: meta-xilinx-core, meta-xilinx-standalone, meta-xilinx-standalone-sdt,
	        meta-microblaze.
	branch: wrynose or AMD release version (e.g. rel-v2026.2)

	URI:
   https://github.com/Xilinx/meta-amd-adaptive-socs (development and AMD release)
	layers: meta-amd-adaptive-socs-bsp
	branch: wrynose or AMD release version (e.g. rel-v2026.2)
