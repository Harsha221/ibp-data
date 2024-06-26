package com.ibp.admin

import org.springframework.web.servlet.support.RequestContextUtils as RCU

@SuppressWarnings('NoTabCharacter')
class BootstrapTagLib {
	static namespace = 'bootstrap'
	public static final String OFFSET = 'offset'
	public static final String MAX = 'max'
	public static final String SPAN_START_END = '<span>...</span>'
	public static final String LI_START_END = '<li><a>...</a></li>'
	public static final String PAGINATER_NEXT = 'paginate.next'
	public static final String SPAN_END = '</span>'
	public static final String LI_END = '</li>'
	public static final String RAQUO = '&raquo;'
	public static final String TOTAL = 'total'
	public static final String MAX_STEPS = 'maxsteps'
	public static final String PAGINATER_PREV = 'paginate.prev'

	/**
	* Creates next/previous links to support pagination for the current controller.<br/>
	*
	* &lt;g:paginate total="${Account.count()}" /&gt;<br/>
	*
	* @emptyTag
	*
	* @attr total REQUIRED The total number of results to paginate
	* @attr action the name of the action to use in the link, if not specified the default action will be linked
	* @attr controller the name of the controller to use in the link, if not specified the current controller will be
	 * linked
	* @attr id The id to use in the link
	* @attr params A map containing request parameters
	* @attr prev The text to display for the previous link (defaults to "Previous" as defined by default.paginate.prev
	 * property in I18n messages.properties)
	* @attr next The text to display for the next link (defaults to "Next" as defined by default.paginate.next property
	 * in I18n messages.properties)
	* @attr max The number of records displayed per page (defaults to 10). Used ONLY if params.max is empty
	* @attr maxsteps The number of steps displayed for pagination (defaults to 10). Used ONLY if params.maxsteps is
	 * empty
	* @attr offset Used only if params.offset is empty
	* @attr fragment The link fragment (often called anchor tag) to use
	*/
	def paginate = { attrs ->
		def writer = out
		if (attrs.total == null) {
			throwTagError('Tag [paginate] is missing required attribute [total]')
		}
		writer << '<ul class="pagination">'
		def messageSource = grailsAttributes.messageSource
		def locale = RCU.getLocale(request)

		def total = attrs.int(TOTAL) ?: 0
		def action = (attrs.action ? attrs.action : (params.action ? params.action : 'index'))
		def offset = params.int(OFFSET) ?: 0
		def max = params.int(MAX)
		def maxsteps = (attrs.int(MAX_STEPS) ?: 10)

//		if (!offset) {
//			offset = (attrs.int(OFFSET) ?: 0)
//		}
		offset = offset ?: attrs.int(OFFSET) ?: 0
		/*if (!max) {
			max = (attrs.int(MAX) ?: 10)
		}*/

		max = max ?: attrs.int(MAX) ?: 10

		def linkParams = [:]
		if (attrs.params) {
			linkParams.putAll(attrs.params)
		}

		linkParams.offset = offset - max
		linkParams.max = max
		if (params.sort) {
			linkParams.sort = params.sort
		}
		if (params.order) {
			linkParams.order = params.order
		}

		def linkTagAttrs = [action:action]
		if (attrs.controller) {
			linkTagAttrs.controller = attrs.controller
		}
		if (attrs.id != null) {
			linkTagAttrs.id = attrs.id
		}
		if (attrs.fragment != null) {
			linkTagAttrs.fragment = attrs.fragment
		}
		//add the mapping attribute if present
		if (attrs.mapping) {
			linkTagAttrs.mapping = attrs.mapping
		}

		linkTagAttrs.params = linkParams

		// determine paging variables
		def steps = maxsteps > 0
		int currentstep = (offset / max) + 1
		int firststep = 1
		int laststep = Math.round(Math.ceil(total / max))

		// display previous link when not on firststep
		if (currentstep > firststep) {
			linkParams.offset = offset - max
			writer << '<li class="paginate_button">'
			writer << link(linkTagAttrs.clone()) {
				(attrs.prev ?: messageSource.getMessage(PAGINATER_PREV, null, '&laquo;', locale))
			}
			writer << LI_END
		}

		// display steps when steps are enabled and laststep is not firststep
		if (steps && laststep > firststep) {
			linkTagAttrs.class = 'step'

			// determine begin and endstep paging variables
			int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2)
			int endstep = currentstep + Math.round(maxsteps / 2) - 1

			if (beginstep < firststep) {
				beginstep = firststep
				endstep = maxsteps
			}
			if (endstep > laststep) {
				beginstep = laststep - maxsteps + 1
				if (beginstep < firststep) {
					beginstep = firststep
				}
				endstep = laststep
			}

			// display firststep link when beginstep is not firststep
			if (beginstep > firststep) {
				linkParams.offset = 0
				writer << '<li class="paginate_button">'
				writer << link(linkTagAttrs.clone()) { firststep.toString() }
				writer << LI_END
				writer << LI_START_END
			}

			// display paginate steps
			(beginstep..endstep).each { i ->
				if (currentstep == i) {
					writer << "<li class='paginate_button active'><a>${i}</a></li>"
				}
				else {
					linkParams.offset = (i - 1) * max
					writer << '<li class="paginate_button">'
					writer << link(linkTagAttrs.clone()) { i.toString() }
					writer << LI_END
				}
			}

			// display laststep link when endstep is not laststep
			if (endstep < laststep) {
				writer << LI_START_END
				linkParams.offset = ((laststep - 1) * max)
				writer << '<li class="paginate_button">'
				writer << link(linkTagAttrs.clone()) { laststep.toString() }
				writer << LI_END
			}
		}

		// display next link when not on laststep
		if (currentstep < laststep) {
			linkParams.offset = offset + max
			writer << '<li class="paginate_button next">'
			writer << link(linkTagAttrs.clone()) {
				(attrs.next ? attrs.next : messageSource.getMessage(PAGINATER_NEXT, null, RAQUO, locale))
			}
			writer << LI_END
		}
		else {
			linkParams.offset = offset + max
			writer << '<li class="paginate_button next">'
			writer << '<a>' + (attrs.next ? attrs.next : messageSource.getMessage(PAGINATER_NEXT, null, RAQUO, locale)) + '</a>'
			writer << LI_END
		}
		writer << '</ul>'
	}

	/**
	 *
	 * @attr body REQUIRED The body must be breadcrumbLink
	 */
	def breadcrumb = { attrs, body ->
		out << '<ol class="breadcrumb">'
		out << body()
		out << '</ol>'
	}

	/**
	 * @emptyTag
	 *
	 * @attr value REQUIRED The value is the value of the link
	 * @attr active True if the link is active
	 */
	def breadcrumbLink = { attrs ->
		def value = attrs.value
		def active = attrs.boolean('active')
		def link = attrs.link ?: '#'
		if (active) {
			out << "<li class=\"active\">${value}</li>"
		} else {
			out << "<li><a href=\"${link}\">${value}</a></li>"
		}
	}

	/**
	 * Creates next/previous links to support pagination for the current controller.<br/>
	 *
	 * &lt;g:remotePaginate total="${Account.count()}" /&gt;<br/>
	 *
	 * @emptyTag
	 *
	 * @attr total REQUIRED The total number of results to paginate
	 * @attr action the name of the action to use in the link, if not specified the default action will be linked
	 * @attr controller the name of the controller to use in the link, if not specified the current controller will be
	 * linked
	 * @attr id The id to use in the link
	 * @attr params A map containing request parameters
	 * @attr prev The text to display for the previous link (defaults to "Previous" as defined by default.paginate.prev
	 * property in I18n messages.properties)
	 * @attr next The text to display for the next link (defaults to "Next" as defined by default.paginate.next
	 * property in I18n messages.properties)
	 * @attr max The number of records displayed per page (defaults to 10). Used ONLY if params.max is empty
	 * @attr maxsteps The number of steps displayed for pagination (defaults to 10). Used ONLY if params.maxsteps
	 * is empty
	 * @attr offset Used only if params.offset is empty
	 * @attr fragment The link fragment (often called anchor tag) to use
	 */
	@SuppressWarnings('DuplicateStringLiteral')
	def remotePaginate = { attrs ->
		def writer = out
		if (attrs.total == null) {
			throwTagError('Tag [paginate] is missing required attribute [total]')
		}
		writer << "<ul class='pagination ${attrs.class ?: ''}'>"
		def messageSource = grailsAttributes.messageSource
		def locale = RCU.getLocale(request)

		def total = attrs.int(TOTAL) ?: 0
		def action = (attrs.action ? attrs.action : (params.action ? params.action : 'index'))
		def offset = params.int(OFFSET) ?: 0
		def max = params.int(MAX)
		def maxsteps = (attrs.int(MAX_STEPS) ?: 10)

		/*if (!offset) {
			offset = (attrs.int(OFFSET) ?: 0)
		}*/
		/*if (!max) {
			max = (attrs.int(MAX) ?: 10)
		}*/

		offset = offset ?: attrs.int(OFFSET) ?: 0
		max = max ?: attrs.int(MAX) ?: 10

		def linkParams = [:]
		if (attrs.params) {
			linkParams.putAll(attrs.params)
		}
		linkParams.offset = offset - max
		linkParams.max = max
		if (params.sort) {
			linkParams.sort = params.sort
		}
		if (params.order) {
			linkParams.order = params.order
		}

		def linkTagAttrs = attrs //[action:action]
		linkTagAttrs.action = action
		if (attrs.controller) {
			linkTagAttrs.controller = attrs.controller
		}
		if (attrs.id != null) {
			linkTagAttrs.id = attrs.id
		}
		if (attrs.fragment != null) {
			linkTagAttrs.fragment = attrs.fragment
		}
		//add the mapping attribute if present
		if (attrs.mapping) {
			linkTagAttrs.mapping = attrs.mapping
		}

		linkTagAttrs.params = linkParams

		// determine paging variables
		def steps = maxsteps > 0
		int currentstep = (offset / max) + 1
		int firststep = 1
		int laststep = Math.round(Math.ceil(total / max))

		// display previous link when not on firststep
		if (currentstep > firststep) {
			linkParams.offset = offset - max
			writer << '<li class="paginate_button">'
			writer << remoteLink(linkTagAttrs.clone()) {
				(attrs.prev ?: messageSource.getMessage(PAGINATER_PREV, null, '&laquo;', locale))
			}
			writer << LI_END
		}

		// display steps when steps are enabled and laststep is not firststep
		if (steps && laststep > firststep) {
			linkTagAttrs.class = 'step'

			// determine begin and endstep paging variables
			int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2)
			int endstep = currentstep + Math.round(maxsteps / 2) - 1

			if (beginstep < firststep) {
				beginstep = firststep
				endstep = maxsteps
			}
			if (endstep > laststep) {
				beginstep = laststep - maxsteps + 1
				if (beginstep < firststep) {
					beginstep = firststep
				}
				endstep = laststep
			}

			// display firststep link when beginstep is not firststep
			if (beginstep > firststep) {
				linkParams.offset = 0
				writer << '<li class="paginate_button">'
				writer << remoteLink(linkTagAttrs.clone()) { firststep.toString() }
				writer << LI_END
				writer << LI_START_END
			}

			// display paginate steps
			(beginstep..endstep).each { i ->
				if (currentstep == i) {
					writer << "<li class='paginate_button active'><a>${i}</a></li>"
				}
				else {
					linkParams.offset = (i - 1) * max
					writer << '<li class="paginate_button">'
					writer << remoteLink(linkTagAttrs.clone()) { i.toString() }
					writer << LI_END

				}
			}

			// display laststep link when endstep is not laststep
			if (endstep < laststep) {
				writer << LI_START_END
				linkParams.offset = (laststep - 1) * max
				writer << '<li class="paginate_button">'
				writer << remoteLink(linkTagAttrs.clone()) { laststep.toString() }
				writer << LI_END
			}
		}

		// display next link when not on laststep
		if (currentstep < laststep) {
			linkParams.offset = offset + max
			writer << '<li class="paginate_button next">'
			writer << remoteLink(linkTagAttrs.clone()) {
				(attrs.next ? attrs.next : messageSource.getMessage(PAGINATER_NEXT, null, RAQUO, locale))
			}
			writer << LI_END
		}
		else {
			linkParams.offset = offset + max
		}
		writer << '</ul>'
	}
}
