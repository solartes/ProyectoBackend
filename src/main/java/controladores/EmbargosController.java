package controladores;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import client.CAClient;
import client.ChannelClient;
import client.FabricClient;
import config.Config;
import invocations.QueryChaincode;
import user.UserContext;
import util.Util;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.EventHub;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.ChaincodeResponse.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Autoridad;
import modelo.Demandado;
import modelo.Embargo;
import util.AnnotationExclusionStrategy;

public class EmbargosController {
	private static final byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
	private static final String EXPECTED_EVENT_NAME = "event";
	
	public static void guardarEmbargo(Embargo embargo) {
		try {
			Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
			String embargoJson=gson.toJson(embargo);
            Util.cleanUp();
			String caUrl = Config.CA_ORG1_URL;
			CAClient caClient = new CAClient(caUrl, null);
			// Enroll Admin to Org1MSP
			UserContext adminUserContext = new UserContext();
			adminUserContext.setName(Config.ADMIN);
			adminUserContext.setAffiliation(Config.ORG1);
			adminUserContext.setMspId(Config.ORG1_MSP);
			caClient.setAdminUserContext(adminUserContext);
			adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);
			
			FabricClient fabClient = new FabricClient(adminUserContext);
			
			ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
			Channel channel = channelClient.getChannel();
			Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://localhost:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();

			TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
			ChaincodeID ccid = ChaincodeID.newBuilder().setName(Config.CHAINCODE_1_NAME).build();
			request.setChaincodeID(ccid);
			request.setFcn("crearEmbargo");
			String[] arguments = {embargoJson};
			request.setArgs(arguments);
			request.setProposalWaitTime(2000);

			Map<String, byte[]> tm2 = new HashMap<>();
			tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8)); 																								
			tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8)); 
			tm2.put("result", ":)".getBytes(UTF_8));
			tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA); 
			request.setTransientMap(tm2);
			Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
			for (ProposalResponse res: responses) {
				Status status = res.getStatus();
				Logger.getLogger(EmbargosController.class.getName()).log(Level.INFO,"Embargo creado "+Config.CHAINCODE_1_NAME + ". Status - " + status);
			}
									
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarAutoridad(Autoridad autoridad) {
		try {
			Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
			String autoridadJson=gson.toJson(autoridad);
            Util.cleanUp();
			String caUrl = Config.CA_ORG1_URL;
			CAClient caClient = new CAClient(caUrl, null);
			// Enroll Admin to Org1MSP
			UserContext adminUserContext = new UserContext();
			adminUserContext.setName(Config.ADMIN);
			adminUserContext.setAffiliation(Config.ORG1);
			adminUserContext.setMspId(Config.ORG1_MSP);
			caClient.setAdminUserContext(adminUserContext);
			adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);
			
			FabricClient fabClient = new FabricClient(adminUserContext);
			
			ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
			Channel channel = channelClient.getChannel();
			Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://localhost:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();

			TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
			ChaincodeID ccid = ChaincodeID.newBuilder().setName(Config.CHAINCODE_1_NAME).build();
			request.setChaincodeID(ccid);
			request.setFcn("crearAutoridad");
			String[] arguments = {autoridadJson};
			request.setArgs(arguments);
			request.setProposalWaitTime(2000);

			Map<String, byte[]> tm2 = new HashMap<>();
			tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8)); 																								
			tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8)); 
			tm2.put("result", ":)".getBytes(UTF_8));
			tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA); 
			request.setTransientMap(tm2);
			Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
			for (ProposalResponse res: responses) {
				Status status = res.getStatus();
				Logger.getLogger(EmbargosController.class.getName()).log(Level.INFO,"Autoridad creada "+Config.CHAINCODE_1_NAME + ". Status - " + status);
			}
									
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static String obtenerEmbargo(String idEmbargo) {
		String stringResponse="";
		try {
            Util.cleanUp();
			String caUrl = Config.CA_ORG1_URL;
			CAClient caClient = new CAClient(caUrl, null);
			// Enroll Admin to Org1MSP
			UserContext adminUserContext = new UserContext();
			adminUserContext.setName(Config.ADMIN);
			adminUserContext.setAffiliation(Config.ORG1);
			adminUserContext.setMspId(Config.ORG1_MSP);
			caClient.setAdminUserContext(adminUserContext);
			adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);
			
			FabricClient fabClient = new FabricClient(adminUserContext);
			
			ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
			Channel channel = channelClient.getChannel();
			Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://localhost:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();


			Thread.sleep(10000);
			String[] args1 = {idEmbargo};
			Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "Querying for a car - " + args1[0]);
			
			Collection<ProposalResponse>  responses1Query = channelClient.queryByChainCode("embargos", "consultarEmbargo", args1);
			
			for (ProposalResponse pres : responses1Query) {				
				String tmp = new String(pres.getChaincodeActionResponsePayload());
				stringResponse+=tmp;
				Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "STRING"+stringResponse);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringResponse;
	}
	
	public static String obtenerAutoridad(String idAutoridad) {
		String stringResponse ="";
		try {
            Util.cleanUp();
			String caUrl = Config.CA_ORG1_URL;
			CAClient caClient = new CAClient(caUrl, null);
			// Enroll Admin to Org1MSP
			UserContext adminUserContext = new UserContext();
			adminUserContext.setName(Config.ADMIN);
			adminUserContext.setAffiliation(Config.ORG1);
			adminUserContext.setMspId(Config.ORG1_MSP);
			caClient.setAdminUserContext(adminUserContext);
			adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);
			
			FabricClient fabClient = new FabricClient(adminUserContext);
			
			ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
			Channel channel = channelClient.getChannel();
			Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://localhost:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();


			Thread.sleep(10000);
			String[] args1 = {idAutoridad};
			Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "Querying for a car - " + args1[0]);
			
			Collection<ProposalResponse>  responses1Query = channelClient.queryByChainCode("embargos", "consultarAutoridad", args1);
			for (ProposalResponse pres : responses1Query) {				
				String tmp = new String(pres.getChaincodeActionResponsePayload());
				stringResponse += tmp;
				Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "STRING"+stringResponse);
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringResponse;
	}
	
	public static String obtenerPersona(String idPersona,String tipoIdentificacion) {
		String stringResponse="";
		try {
            Util.cleanUp();
			String caUrl = Config.CA_ORG1_URL;
			CAClient caClient = new CAClient(caUrl, null);
			// Enroll Admin to Org1MSP
			UserContext adminUserContext = new UserContext();
			adminUserContext.setName(Config.ADMIN);
			adminUserContext.setAffiliation(Config.ORG1);
			adminUserContext.setMspId(Config.ORG1_MSP);
			caClient.setAdminUserContext(adminUserContext);
			adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);
			
			FabricClient fabClient = new FabricClient(adminUserContext);
			
			ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
			Channel channel = channelClient.getChannel();
			Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://localhost:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();


			Thread.sleep(10000);
			String[] args1 = {idPersona,tipoIdentificacion};
			Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "Querying for a car - " + args1[0]);
			
			Collection<ProposalResponse>  responses1Query = channelClient.queryByChainCode("embargos", "consultarPersona", args1);
			for (ProposalResponse pres : responses1Query) {				
				String tmp = new String(pres.getChaincodeActionResponsePayload());
				stringResponse += tmp;
				Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "STRING"+stringResponse);
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringResponse;
	}
	
	public static String consultaGeneral(String consulta) {
		String stringResponse="";
		try {
            Util.cleanUp();
			String caUrl = Config.CA_ORG1_URL;
			CAClient caClient = new CAClient(caUrl, null);
			// Enroll Admin to Org1MSP
			UserContext adminUserContext = new UserContext();
			adminUserContext.setName(Config.ADMIN);
			adminUserContext.setAffiliation(Config.ORG1);
			adminUserContext.setMspId(Config.ORG1_MSP);
			caClient.setAdminUserContext(adminUserContext);
			adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);
			
			FabricClient fabClient = new FabricClient(adminUserContext);
			
			ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
			Channel channel = channelClient.getChannel();
			Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://localhost:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();


			Thread.sleep(10000);
			String[] args1 = {consulta};
			Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "Querying for a car - " + args1[0]);
			
			Collection<ProposalResponse>  responses1Query = channelClient.queryByChainCode("embargos", "consultarEmbargoPorCampo", args1);
			for (ProposalResponse pres : responses1Query) {				
				String tmp = new String(pres.getChaincodeActionResponsePayload());
				stringResponse += tmp;
				Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "STRING"+stringResponse);
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringResponse;
	}
}
