/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

package eu.stratosphere.nephele.taskmanager.transferenvelope;

import java.io.IOException;

/**
 * A transfer envelope dispatcher receives {@link TransferEnvelopes} and sends them to all of this destinations.
 * 
 * @author warneke
 */
public interface TransferEnvelopeDispatcher {

	/**
	 * Processes a transfer envelope from an output channel. The method may block until the system has allocated enough
	 * resources to further process the envelope.
	 * 
	 * @param transferEnvelope
	 *        the transfer envelope to be processed
	 * @throws IOException
	 *         thrown if an I/O error occurs while allocating the resources to further process the envelope
	 * @throws InterruptedException
	 *         thrown if the thread is interrupted while waiting for the processing to complete
	 */
	void processEnvelopeFromOutputChannel(TransferEnvelope transferEnvelope) throws IOException, InterruptedException;

	void processEnvelopeFromInputChannel(TransferEnvelope transferEnvelope) throws IOException, InterruptedException;

	/**
	 * Processes a transfer envelope from an incoming network connection or a checkpoint. The method will return
	 * immediately even if not enough resources for further processing could be allocated. In this case the method must
	 * be called again with the same envelope. The success of the method call is indicated by the return value.
	 * 
	 * @param transferEnvelope
	 *        the transfer envelope to be processed
	 * @return <code>true</code> if enough resources could be allocated to further process the envelope or
	 *         <code>false</code> if resources to further process the envelope are currently missing. In the latter case
	 *         the method must be called again with the same envelope.
	 * @throws IOException
	 *         thrown if an error occurs while allocating the resources to further process the envelope
	 */
	boolean processEnvelopeFromNetworkOrCheckpoint(TransferEnvelope transferEnvelope) throws IOException;
}
