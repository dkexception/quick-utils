package com.dkexception.quickutils.utils.extensions

fun String.sanitizeWhatsappNumber(): String {
	replace(" ", "")
	replace("+", "")
	return this
}
