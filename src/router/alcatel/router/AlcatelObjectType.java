package router.alcatel.router;

/**
 * Enum for object types.  Each new object should be added here.
 * @author pete
 *
 */
public enum AlcatelObjectType { 
	
	NONE,
	HARDWAREOBJECT,
	CHASSIS,
	
	BANDWIDTHPOLICY,
	
	IOM, 
	MDA, 
	FPINGRESS,
	MDANETWORK,
	MDAACCESS,
	MDANETWORKINGRESS,
	MCASTPATHMANAGEMENT,
	
	CPM,
	IOMFP,
	XF, 
	PHYSICALPORT,
	PORTETHERNET,
	ETHERNETNETWORK,
	LAG,
	QOSPOLICY,
	
	NETWORKQOSPOLICY,
	NETWORKQUEUEQOSPOLICY,
	SAPQOSPOLICY,
	SAPINGRESSQOSPOLICY,
	SAPEGRESSQOSPOLICY,
	
	//NETWORKQOSPOLICYQUEUE,  // Network policy doesn't have queue
	SAPQOSPOLICYQUEUE,
	NETWORKQUEUEQOSPOLICYQUEUE,
	ROUTERINTERFACE,
	ROUTERNETWORKINTERFACE,
	STATICROUTE,
	
	OSPFAREA,
	OSPFINTERFACE,
	
	PIMINTERFACE,
	IGMPINTERFACE,
	MPLSINTERFACE,
	MPLSPATH,
	MPLSLSP,
	RSVPINTERFACE,
	LDPINTERFACE,
	
	SERVICE,
	SDPOBJECT,
	VPLSSERVICE,
	IESSERVICE,
	VPRNSERVICE,
	EPIPESERVICE,
	SERVICEINTERFACE,
	
	SAPOBJECT,
	SAPINGRESSOBJECT,
	SAPEGRESSOBJECT,
	SERVICESDPOBJECT,
	SPOKESDPOBJECT,
	MESHSDPOBJECT,
	SDPINGRESS,
	SDPEGRESS,
	
	POLICYCOMMUNITY,
	POLICYSTATEMENT,
	
	BGPGROUP,
	};

