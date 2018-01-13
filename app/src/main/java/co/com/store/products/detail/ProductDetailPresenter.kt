package co.com.store.products.detail

import android.os.Bundle
import co.com.core.ShoppingCart
import co.com.core.use_cases.Product
import co.com.data.PRODUCT
import co.com.store.R
import co.com.store.products.ProductCallbacks

/**
 * Created by oscarg798 on 1/12/18.
 */
class ProductDetailPresenter : IProductDetailPresenter {

    private var mView: IProductDetailView? = null

    private var mProduct: Product? = null

    private var mQuantity = 1

    private var mIsProductInCart = false

    override fun bind(view: IProductDetailView) {
        mView = view
        mView?.initComponents()
        mProduct?.let {
            mQuantity = if (ShoppingCart.instance
                    .getQuantityForProduct(mProduct!!.mUuid) != 0)
                ShoppingCart.instance
                        .getQuantityForProduct(mProduct!!.mUuid) else 1
            mIsProductInCart = ShoppingCart.instance.isProductOnCart(mProduct!!.mUuid)
            with(mProduct!!) {
                mView?.setProductName(mName)
                mView?.setProductDescription(mDescription)
                mView?.setPrice("Price: $${mProduct!!.mPrice}")
                if (mImages !== null && mImages!!.isNotEmpty()) {
                    mView?.setProductImage(mImages!![0])
                }
            }
            calculateBTNAddLabelFromQuantity()


        }
    }

    override fun onCreate(bundle: Bundle?) {
        bundle?.let {
            mProduct = bundle.getParcelable(PRODUCT)
        }
    }

    private fun calculateBTNAddLabelFromQuantity() {
        mProduct?.let {
            mView?.setProductQuantity(mQuantity.toString())

            val resourceID = if (!mIsProductInCart && mQuantity > 0) {
                R.string.add_to_cart_label
            } else if (mIsProductInCart && mQuantity <= 0) {
                mQuantity = 0
                R.string.remove_from_cart_label

            } else if(mIsProductInCart && mQuantity>0 && mQuantity != ShoppingCart.instance
                    .getQuantityForProduct(mProduct!!.mUuid)){
                R.string.update_cart_label
            }

            else {
                R.string.in_cart_label
            }
            mView?.setProductQuantity(mQuantity.toString())

            mView?.setBTNAddToCartText(resourceID)

        }



    }


    override fun addOrRemoveFromQuantity(add: Boolean) {
        when (add) {
            true -> mQuantity++
            else -> mQuantity--
        }
        calculateBTNAddLabelFromQuantity()

    }

    override fun btnAddToCartPressed() {
        mProduct?.let {
            ShoppingCart.instance.addOrRemoveProductFromShoppingCart(mProduct!!,
                    if (mIsProductInCart && mQuantity<=0) 0 else mQuantity)

        }

    }

    override fun onDestroy() {
        mView = null
    }
}