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
	IOM, 
	MDA, 
	CPM,
	XF, 
	PHYSICALPORT,
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
	
	SERVICE,
	SDPSERVICE,
	VPLSSERVICE,
	IESSERVICE,
	VPRNSERVICE,
	EPIPESERVICE,
	SAPOBJECT,
	
	};

