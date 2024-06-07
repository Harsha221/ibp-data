package com.ibp.admin

class FormTagLib {
	static namespace = 'form'

	def validateForm = { attrs, body ->
		attrs['className'] = attrs.class
		String html = render template: '/common/tags-template/validateForm', model: attrs, body
		out << html
	}

	def validateUploadForm = { attrs, body ->
		attrs['className'] = attrs.class
		String html = render template: '/common/tags-template/validateUploadForm', model: attrs, body
		out << html
	}

	def input = { attrs, body ->
		attrs['className'] = attrs.class
		String html = render template: '/common/tags-template/input', model: attrs, body
		out << html
	}

	def textarea = { attrs, body ->
		attrs['className'] = attrs.class
		String html = render template: '/common/tags-template/textarea', model: attrs, body
		out << html
	}

	def select = { attrs, body ->
		attrs['className'] = attrs.class
		String html = render template: '/common/tags-template/select', model: attrs, body
		out << html
	}
}
