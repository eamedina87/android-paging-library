package ec.erickmedina.paging_test.exceptions

import java.io.IOException

class NoConnectivityException(override var message: String) : IOException(message)
