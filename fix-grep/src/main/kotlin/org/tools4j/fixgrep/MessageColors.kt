package org.tools4j.fixgrep

import org.tools4j.fix.ExecType
import org.tools4j.fix.Fields
import org.tools4j.fix.FixMessageType

/**
 * User: ben
 * Date: 21/03/2018
 * Time: 6:30 AM
 */
class MessageColors {
    val colorsByMsgCodeAndExecType: Map<String, AnsiColor> by lazy {
        val map = HashMap<String, AnsiColor>()
        for(msgType in FixMessageType.values()){
            if(msgType == FixMessageType.ExecutionReport){
                for(execType in ExecType.values()){
                    map[Fields.Companion.getMsgTypeAndExecTypeKey(msgType, execType)] = getColor(execType)
                }
            } else {
                map[msgType.code] = getColor(msgType)
            }
        }
        map
    }

    fun getColor(fields: Fields): AnsiColor{
        return colorsByMsgCodeAndExecType[fields.msgTypeAndExecTypeKey] ?: AnsiColor.Reset
    }

    fun getColor(msgType: FixMessageType): AnsiColor{
        return when (msgType) {
            FixMessageType.NewOrderSingle
            -> AnsiColor.Blue

            FixMessageType.Heartbeat,
            FixMessageType.TestRequest,
            FixMessageType.ResendRequest,
            FixMessageType.Reject,
            FixMessageType.SequenceReset,
            FixMessageType.Logout,
            FixMessageType.IOI,
            FixMessageType.Advertisement,
            FixMessageType.OrderCancelReject,
            FixMessageType.Logon,
            FixMessageType.News,
            FixMessageType.Email,
            FixMessageType.NewOrderList,
            FixMessageType.OrderCancelRequest,
            FixMessageType.OrderCancelReplaceRequest,
            FixMessageType.OrderStatusRequest,
            FixMessageType.AllocationInstruction,
            FixMessageType.ListCancelRequest,
            FixMessageType.ListExecute,
            FixMessageType.ListStatusRequest,
            FixMessageType.ListStatus,
            FixMessageType.AllocationInstructionAck,
            FixMessageType.DontKnowTradeDK,
            FixMessageType.QuoteRequest,
            FixMessageType.Quote,
            FixMessageType.SettlementInstructions,
            FixMessageType.MarketDataRequest,
            FixMessageType.MarketDataSnapshotFullRefresh,
            FixMessageType.MarketDataIncrementalRefresh,
            FixMessageType.MarketDataRequestReject,
            FixMessageType.QuoteCancel,
            FixMessageType.QuoteStatusRequest,
            FixMessageType.MassQuoteAcknowledgement,
            FixMessageType.SecurityDefinitionRequest,
            FixMessageType.SecurityDefinition,
            FixMessageType.SecurityStatusRequest,
            FixMessageType.SecurityStatus,
            FixMessageType.TradingSessionStatusRequest,
            FixMessageType.TradingSessionStatus,
            FixMessageType.MassQuote,
            FixMessageType.BusinessMessageReject,
            FixMessageType.BidRequest,
            FixMessageType.BidResponse,
            FixMessageType.ListStrikePrice,
            FixMessageType.RegistrationInstructions,
            FixMessageType.RegistrationInstructionsResponse,
            FixMessageType.OrderMassCancelRequest,
            FixMessageType.OrderMassCancelReport,
            FixMessageType.NewOrderCross,
            FixMessageType.CrossOrderCancelReplaceRequest,
            FixMessageType.CrossOrderCancelRequest,
            FixMessageType.SecurityTypeRequest,
            FixMessageType.SecurityTypes,
            FixMessageType.SecurityListRequest,
            FixMessageType.SecurityList,
            FixMessageType.DerivativeSecurityListRequest,
            FixMessageType.DerivativeSecurityList,
            FixMessageType.NewOrderMultileg,
            FixMessageType.MultilegOrderCancelReplace,
            FixMessageType.TradeCaptureReportRequest,
            FixMessageType.TradeCaptureReport,
            FixMessageType.OrderMassStatusRequest,
            FixMessageType.QuoteRequestReject,
            FixMessageType.RFQRequest,
            FixMessageType.QuoteStatusReport,
            FixMessageType.QuoteResponse,
            FixMessageType.Confirmation,
            FixMessageType.PositionMaintenanceRequest,
            FixMessageType.PositionMaintenanceReport,
            FixMessageType.RequestForPositions,
            FixMessageType.RequestForPositionsAck,
            FixMessageType.PositionReport,
            FixMessageType.TradeCaptureReportRequestAck,
            FixMessageType.TradeCaptureReportAck,
            FixMessageType.AllocationReport,
            FixMessageType.AllocationReportAck,
            FixMessageType.Confirmation_Ack,
            FixMessageType.SettlementInstructionRequest,
            FixMessageType.AssignmentReport,
            FixMessageType.CollateralRequest,
            FixMessageType.CollateralAssignment,
            FixMessageType.CollateralResponse,
            FixMessageType.CollateralReport,
            FixMessageType.CollateralInquiry,
            FixMessageType.NetworkCounterpartySystemStatusRequest,
            FixMessageType.NetworkCounterpartySystemStatusResponse,
            FixMessageType.UserRequest,
            FixMessageType.UserResponse,
            FixMessageType.CollateralInquiryAck,
            FixMessageType.ConfirmationRequest,
            FixMessageType.ContraryIntentionReport,
            FixMessageType.SecurityDefinitionUpdateReport,
            FixMessageType.SecurityListUpdateReport,
            FixMessageType.AdjustedPositionReport,
            FixMessageType.AllocationInstructionAlert,
            FixMessageType.ExecutionAcknowledgement,
            FixMessageType.TradingSessionList,
            FixMessageType.TradingSessionListRequest,
            FixMessageType.SettlementObligationReport,
            FixMessageType.DerivativeSecurityListUpdateReport,
            FixMessageType.TradingSessionListUpdateReport,
            FixMessageType.MarketDefinitionRequest,
            FixMessageType.MarketDefinition,
            FixMessageType.MarketDefinitionUpdateReport,
            FixMessageType.UserNotification,
            FixMessageType.OrderMassActionReport,
            FixMessageType.OrderMassActionRequest,
            FixMessageType.ApplicationMessageRequest,
            FixMessageType.ApplicationMessageRequestAck,
            FixMessageType.ApplicationMessageReport,
            FixMessageType.StreamAssignmentRequest,
            FixMessageType.StreamAssignmentReport,
            FixMessageType.StreamAssignmentReportACK,
            FixMessageType.PartyDetailsListRequest,
            FixMessageType.PartyDetailsListReport
            -> AnsiColor.Purple
            else -> AnsiColor.Purple
        }    
    }

    private fun getColor(execType: ExecType): AnsiColor {
        return when(execType){
            ExecType.New,
            ExecType.PartialFill,
            ExecType.Fill,
            ExecType.Replaced
                    ->AnsiColor.Cyan

            ExecType.DoneForDay
                    ->AnsiColor.Green

            ExecType.Canceled,
            ExecType.Stopped,
            ExecType.Rejected,
            ExecType.Suspended
                    ->AnsiColor.Red

            ExecType.PendingCancel,
            ExecType.PendingNew,
            ExecType.PendingReplace
                    -> AnsiColor.Yellow

            ExecType.TradeCancel,
            ExecType.Expired
                    -> AnsiColor.Reset

            ExecType.Calculated,
            ExecType.Trade,
            ExecType.TradeCorrect,
            ExecType.OrderStatus,
            ExecType.TradeInAClearingHold,
            ExecType.TradeHasBeenReleasedToClearing,
            ExecType.TriggeredOrActivatedBySystem
                    -> AnsiColor.Reset
            else -> AnsiColor.Reset
        }
    }
}