SUMMARY = "OpenBMC for AMD Adaptive SoC's BMC - Applications"

PR = "r1"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
        ${PN}-chassis \
        ${PN}-fans \
        ${PN}-flash \
        ${PN}-system \
        "

PROVIDES += "virtual/obmc-chassis-mgmt"
PROVIDES += "virtual/obmc-fan-mgmt"
PROVIDES += "virtual/obmc-flash-mgmt"
PROVIDES += "virtual/obmc-system-mgmt"

RPROVIDES:${PN}-chassis += "virtual-obmc-chassis-mgmt"
RPROVIDES:${PN}-fans += "virtual-obmc-fan-mgmt"
RPROVIDES:${PN}-flash += "virtual-obmc-flash-mgmt"
RPROVIDES:${PN}-system += "virtual-obmc-system-mgmt"

SUMMARY:${PN}-chassis = "AMD Adaptive SoC's BMC Chassis"
RDEPENDS:${PN}-chassis = " \
        "

SUMMARY:${PN}-fans = "AMD Adaptive SoC's BMC Fans"
RDEPENDS:${PN}-fans = " \
        "

SUMMARY:${PN}-flash = "AMD Adaptive SoC's BMC Flash"
RDEPENDS:${PN}-flash = " \
        "

SUMMARY:${PN}-system = "AMD Adaptive SoC's BMC System"
RDEPENDS:${PN}-system = " \
        "
