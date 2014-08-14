/******************************************************************************
 * Spine Runtimes Software License
 * Version 2.1
 * 
 * Copyright (c) 2013, Esoteric Software
 * All rights reserved.
 * 
 * You are granted a perpetual, non-exclusive, non-sublicensable and
 * non-transferable license to install, execute and perform the Spine Runtimes
 * Software (the "Software") solely for internal use. Without the written
 * permission of Esoteric Software (typically granted by licensing Spine), you
 * may not (a) modify, translate, adapt or otherwise create derivative works,
 * improvements of the Software or develop new applications using the Software
 * or (b) remove, delete, alter or obscure any trademarks or any copyright,
 * trademark, patent or other intellectual property or proprietary rights
 * notices on or in the Software, including any copy thereof. Redistributions
 * in binary or source form must include this license and terms.
 * 
 * THIS SOFTWARE IS PROVIDED BY ESOTERIC SOFTWARE "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL ESOTERIC SOFTARE BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *****************************************************************************/

package com.esotericsoftware.spine;

import static com.badlogic.gdx.math.Matrix3.*;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;

public class Bone {
	final BoneData data;
	final Bone parent;
	float x, y;
	float rotation, rotationIK;
	float scaleX, scaleY;
	boolean flipX, flipY;

	float m00, m01, worldX; // a b x
	float m10, m11, worldY; // c d y
	float worldRotation;
	float worldScaleX, worldScaleY;

	/** @param parent May be null. */
	public Bone (BoneData data, Bone parent) {
		if (data == null) throw new IllegalArgumentException("data cannot be null.");
		this.data = data;
		this.parent = parent;
		setToSetupPose();
	}

	/** Copy constructor.
	 * @param parent May be null. */
	public Bone (Bone bone, Bone parent) {
		if (bone == null) throw new IllegalArgumentException("bone cannot be null.");
		this.parent = parent;
		data = bone.data;
		x = bone.x;
		y = bone.y;
		rotation = bone.rotation;
		rotationIK = bone.rotationIK;
		scaleX = bone.scaleX;
		scaleY = bone.scaleY;
		flipX = bone.flipX;
		flipY = bone.flipY;
	}

	/** Computes the world SRT using the parent bone and the local SRT. */
	public void updateWorldTransform () {
		Bone parent = this.parent;
		float x = this.x, y = this.y;
		if (parent != null) {
			worldX = x * parent.m00 + y * parent.m01 + parent.worldX;
			worldY = x * parent.m10 + y * parent.m11 + parent.worldY;
			if (data.inheritScale) {
				worldScaleX = parent.worldScaleX * scaleX;
				worldScaleY = parent.worldScaleY * scaleY;
			} else {
				worldScaleX = scaleX;
				worldScaleY = scaleY;
			}
			worldRotation = data.inheritRotation ? parent.worldRotation + rotationIK : rotationIK;
		} else {
			worldX = flipX ? -x : x;
			worldY = flipY ? -y : y;
			worldScaleX = scaleX;
			worldScaleY = scaleY;
			worldRotation = rotationIK;
		}
		float cos = MathUtils.cosDeg(worldRotation);
		float sin = MathUtils.sinDeg(worldRotation);
		if (flipX) {
			m00 = -cos * worldScaleX;
			m01 = sin * worldScaleY;
		} else {
			m00 = cos * worldScaleX;
			m01 = -sin * worldScaleY;
		}
		if (flipY) {
			m10 = -sin * worldScaleX;
			m11 = -cos * worldScaleY;
		} else {
			m10 = sin * worldScaleX;
			m11 = cos * worldScaleY;
		}
	}

	public void setToSetupPose () {
		BoneData data = this.data;
		x = data.x;
		y = data.y;
		rotation = data.rotation;
		rotationIK = rotation;
		scaleX = data.scaleX;
		scaleY = data.scaleY;
	}

	public BoneData getData () {
		return data;
	}

	public Bone getParent () {
		return parent;
	}

	public float getX () {
		return x;
	}

	public void setX (float x) {
		this.x = x;
	}

	public float getY () {
		return y;
	}

	public void setY (float y) {
		this.y = y;
	}

	public void setPosition (float x, float y) {
		this.x = x;
		this.y = y;
	}

	/** Returns the forward kinetics rotation. */
	public float getRotation () {
		return rotation;
	}

	public void setRotation (float rotation) {
		this.rotation = rotation;
	}

	/** Returns the inverse kinetics rotation, as calculated by any IK constraints. */
	public float getRotationIK () {
		return rotationIK;
	}

	public void setRotationIK (float rotationIK) {
		this.rotationIK = rotationIK;
	}

	public float getScaleX () {
		return scaleX;
	}

	public void setScaleX (float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY () {
		return scaleY;
	}

	public void setScaleY (float scaleY) {
		this.scaleY = scaleY;
	}

	public void setScale (float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	public void setScale (float scale) {
		scaleX = scale;
		scaleY = scale;
	}

	public float getM00 () {
		return m00;
	}

	public float getM01 () {
		return m01;
	}

	public float getM10 () {
		return m10;
	}

	public float getM11 () {
		return m11;
	}

	public float getWorldX () {
		return worldX;
	}

	public float getWorldY () {
		return worldY;
	}

	public float getWorldRotation () {
		return worldRotation;
	}

	public float getWorldScaleX () {
		return worldScaleX;
	}

	public float getWorldScaleY () {
		return worldScaleY;
	}

	public Matrix3 getWorldTransform (Matrix3 worldTransform) {
		if (worldTransform == null) throw new IllegalArgumentException("worldTransform cannot be null.");
		float[] val = worldTransform.val;
		val[M00] = m00;
		val[M01] = m01;
		val[M10] = m10;
		val[M11] = m11;
		val[M02] = worldX;
		val[M12] = worldY;
		val[M20] = 0;
		val[M21] = 0;
		val[M22] = 1;
		return worldTransform;
	}

	public Vector2 worldToLocal (Vector2 world) {
		float x = world.x - worldX, y = world.y - worldY;
		float m00 = this.m00, m10 = this.m10, m01 = this.m01, m11 = this.m11;
		if (flipX != flipY) {
			m00 *= -1;
			m11 *= -1;
		}
		float invDet = 1 / (m00 * m11 - m01 * m10);
		world.x = (x * m00 * invDet - y * m01 * invDet);
		world.y = (y * m11 * invDet - x * m10 * invDet);
		return world;
	}

	public Vector2 localToWorld (Vector2 local) {
		float x = local.x, y = local.y;
		local.x = x * m00 + y * m01 + worldX;
		local.y = x * m10 + y * m11 + worldY;
		return local;
	}

	public String toString () {
		return data.name;
	}
}
